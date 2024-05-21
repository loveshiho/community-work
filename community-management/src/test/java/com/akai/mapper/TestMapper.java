package com.akai.mapper;

import com.akai.system.mapper.SysMenuMapper;
import com.akai.system.mapper.SysRoleMapper;
import com.akai.system.mapper.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMapper {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Test
    void test() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", "admin");
        System.out.println(sysUserMapper.selectOne(queryWrapper));
    }

    @Test
    void testRole() {
        List<String> list = sysRoleMapper.selectRolePermissionByUserId(2l);
        list.forEach(System.out::println);
    }

    @Test
    void testPerms() {
        List<String> list = sysMenuMapper.selectMenuPermsByUserId(2l);
        list.forEach(System.out::println);
    }
}
