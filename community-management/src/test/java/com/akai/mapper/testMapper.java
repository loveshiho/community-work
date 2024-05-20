package com.akai.mapper;

import com.akai.system.mapper.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testMapper {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Test
    void test() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", "admin");
        System.out.println(sysUserMapper.selectOne(queryWrapper));
    }
}
