package com.akai.service;

import com.akai.common.ResponseResult;
import com.akai.pojo.SysUser;

public interface LoginService {
    ResponseResult login(SysUser sysUser);

    ResponseResult logout();

    String login(String userName, String password, String code, String uuid);
}
