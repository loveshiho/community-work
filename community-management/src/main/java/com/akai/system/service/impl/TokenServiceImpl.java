package com.akai.system.service.impl;

import com.akai.common.utils.Constants;
import com.akai.common.utils.RedisCache;
import com.akai.common.utils.UUIDUtils;
import com.akai.system.domain.LoginUser;
import com.akai.system.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${token.header}")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expireTime}")
    private int expireTime;

    private static final Long MILLIS_MINUTE = 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String createToken(LoginUser loginUser) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        /*设置用户唯一标识*/
        String uuid = UUIDUtils.simpleUUID();
        loginUser.setToken(uuid);
        /*刷新令牌，缓存用户信息*/
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, uuid);
        /*创建token, 将用户唯一标识通过 setClaims方法保存到 token中*/
        return Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, secret).compact();
    }

    @Override
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        /*过期时间30分钟*/
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        /*根据 uuid将 loginUser缓存*/
        String token = loginUser.getToken();
        String redisKey = Constants.LOGIN_TOKEN_KEY + token;
        redisCache.setCacheObject(redisKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /*解析token*/
    public Claims parserToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @Override
    public LoginUser getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (token != null && token != "") {
            /*解析token*/
            Claims claims = parserToken(token);
            /*解析用户信息*/
            String uuid  = (String) claims.get(Constants.LOGIN_USER_KEY);
            String redisKey = Constants.LOGIN_TOKEN_KEY + uuid;
            LoginUser loginUser = redisCache.getCacheObject(redisKey, LoginUser.class);
            return loginUser;
        }
        return null;
    }

    @Override
    public void verifyToken(LoginUser loginUser) {
        Long expireTime = loginUser.getExpireTime();
        long currentTimeMillis = System.currentTimeMillis();
        /*每隔十分钟，自动刷新*/
        if (expireTime - currentTimeMillis < 20 * MILLIS_MINUTE) {
            refreshToken(loginUser);
        }
    }

    @Override
    public void setLoginUser(LoginUser loginUser) {
        if (loginUser != null && loginUser.getToken() != null && loginUser.getToken() != "") {
            refreshToken(loginUser);
        }
    }

    @Override
    public void delLoginUser(String token) {
        if (token != null && token != "") {
            String redisKey = Constants.LOGIN_TOKEN_KEY + token;
            redisCache.deleteObject(redisKey);
        }
    }

    /*从 request的请求头中获取 token*/
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (token != null && token != "" && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }
}
