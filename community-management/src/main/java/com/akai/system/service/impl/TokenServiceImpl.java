package com.akai.system.service.impl;

import com.akai.common.utils.Constants;
import com.akai.common.utils.UUIDUtils;
import com.akai.system.domain.LoginUser;
import com.akai.system.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${token.header}")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expireTime}")
    private int expireTime;

    @Override
    public String createToken(LoginUser loginUser) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        /*设置用户唯一标识*/
        String uuid = UUIDUtils.simpleUUID();
        loginUser.setToken(uuid);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, uuid);
        /*创建token, 将用户唯一标识通过 setClaims方法保存到 token中*/
        return Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, secret).compact();
    }
}
