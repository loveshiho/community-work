package com.akai.system.service;

import com.akai.system.domain.SysUser;

public interface SysUserService {
    public SysUser selectUserByUserName(String userName);
}
