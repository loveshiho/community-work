package com.akai.system.service.impl;

import com.akai.common.core.exception.BaseException;
import com.akai.common.core.exception.CaptchaNotMatchException;
import com.akai.common.utils.Constants;
import com.akai.common.utils.RedisCache;
import com.akai.system.service.SysLoginService;
import com.akai.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.akai.system.domain.*;

@Service
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public String login(String userName, String password, String code, String uuid) {
        String redisKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String redisCode = redisCache.getCacheObject(redisKey, String.class);
        redisCache.deleteObject(redisKey);
        if (redisCode == null || !code.equalsIgnoreCase(redisCode)) {
            throw new CaptchaNotMatchException("验证码错误");
        }
        Authentication token = new UsernamePasswordAuthenticationToken(userName, password);

        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication == null)
            throw new BaseException("用户名不存在或密码错误");
        /*登录成功*/
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        /*生成jwt*/
        return tokenService.createToken(loginUser);
    }
}
