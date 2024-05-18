package com.akai.controller;

import com.akai.common.ResponseResult;
import com.akai.pojo.SysUser;
import com.akai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/user/login")
    public ResponseResult login(@RequestBody SysUser sysUser) {
        /*登录*/
        return loginService.login(sysUser);
    }
    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        /*登出*/
        return loginService.logout();
    }
}
