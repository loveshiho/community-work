package com.akai;

import com.akai.mapper.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testMapper {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Test
    public void testGetUserList() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        List list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
        /*
        * SysUser{userId=1, userName='shiho', nickName='zhibao', password='1234', phonenumber='110', sex='0', status='0'}
        * SysUser{userId=2, userName='conan', nickName='zhengdan', password='5678', phonenumber='120', sex='1', status='0'}
        * */
    }
}
