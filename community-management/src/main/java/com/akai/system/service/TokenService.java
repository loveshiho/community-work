package com.akai.system.service;

import com.akai.system.domain.LoginUser;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    public String createToken(LoginUser loginUser);
    public void refreshToken(LoginUser loginUser);
    public LoginUser getLoginUser(HttpServletRequest request);
    /*验证令牌有效期，并且实现自动刷新缓存*/
    public void verifyToken(LoginUser loginUser);
    /*设置用户信息*/
    public void setLoginUser(LoginUser loginUser);
    /*删除用户信息*/
    public void delLoginUser(String token);
}
