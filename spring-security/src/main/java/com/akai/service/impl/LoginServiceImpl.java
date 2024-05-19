package com.akai.service.impl;

import com.akai.common.Constants;
import com.akai.common.ResponseResult;
import com.akai.pojo.LoginUser;
import com.akai.pojo.SysUser;
import com.akai.service.LoginService;
import com.akai.utils.JwtUtils;
import com.akai.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(SysUser sysUser) {
        /*使用AuthenticationManager的 authenticate方法 进行用户认证*/
        Authentication token = new UsernamePasswordAuthenticationToken(sysUser.getUserName(), sysUser.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);  /*底层执行了很多调用，内部流程我们是看不到的*/
        /*认证后的对象，包含用户所有信息*/
        // 如果认证没有通过给出错误提示
        if (authentication == null) throw new RuntimeException("登录失败");
        /*如果认证成功，使用 userId，生成一个jwt，并将其保存到Response当中返回*/
        /*1 获取经过身份认证的用户主体信息*/
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        /*2 获取用户ID，生成jwt*/
        String userId = loginUser.getSysUser().getUserId().toString();
        String jwt = JwtUtils.createJwt(userId);
        /*3 将用户信息存储在 Redis中，在下一次请求时能够识别出用户，userId作为key，user作为value*/
        redisCache.setCacheObject("login:" + userId, loginUser);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return new ResponseResult("200", "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loginUser == null) throw new RuntimeException("fucking fucked!");
        Long userId = loginUser.getSysUser().getUserId();
        String redisKey = "login:" + userId;
        redisCache.delCacheObject(redisKey);
        return new ResponseResult("200", "注销成功", null);
    }

    @Override
    public String login(String userName, String password, String code, String uuid) {
        String redisKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String redisCode = redisCache.getCacheObject(redisKey, String.class);
        redisCache.deleteObject(redisKey);
        if (redisCode == null || !code.equalsIgnoreCase(redisCode)) {
            throw new RuntimeException("验证失败");
        }
        /*使用AuthenticationManager的 authenticate方法 进行用户认证*/
        Authentication token = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = authenticationManager.authenticate(token);  /*底层执行了很多调用，内部流程我们是看不到的*/
        /*认证后的对象，包含用户所有信息*/
        // 如果认证没有通过给出错误提示
        if (authentication == null) throw new RuntimeException("登录失败");
        /*如果认证成功，使用 userId，生成一个jwt，并将其保存到Response当中返回*/
        /*1 获取经过身份认证的用户主体信息*/
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        /*2 获取用户ID，生成jwt*/
        String userId = loginUser.getSysUser().getUserId().toString();
        String jwt = JwtUtils.createJwt(userId);
        /*3 将用户信息存储在 Redis中，在下一次请求时能够识别出用户，userId作为key，user作为value*/
        redisCache.setCacheObject("login:" + userId, loginUser);
        return jwt;
    }
}
