package com.akai.service;

import com.akai.system.domain.SysUser;
import com.akai.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestGetUser {
    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testGetUser() {
        SysUser jt = sysUserService.selectUserByUserName("jt");
        System.out.println(jt);
    }
}
