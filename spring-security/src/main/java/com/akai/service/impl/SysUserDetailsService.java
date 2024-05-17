package com.akai.service.impl;

import com.akai.mapper.SysUserMapper;
import com.akai.pojo.LoginUser;
import com.akai.pojo.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * 根据用户名检索用户信息
 * 实现loadUserByUsername
 * */
@Service
public class SysUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;

    /*封装用户信息*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询信息
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        /*查不到数据，跑出异常*/
        if (sysUser == null) {
            throw new  UsernameNotFoundException(username);
        }
        // 方法的返回值是 UserDetails接口类型，需要返回自定义的实现类，并且将 user信息通过构造方法传入
        /*此时loginUser对象已经被加载进入内存*/
        return new LoginUser(sysUser);
    }
}
