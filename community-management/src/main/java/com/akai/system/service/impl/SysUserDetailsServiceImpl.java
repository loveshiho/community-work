package com.akai.system.service.impl;

import com.akai.common.core.exception.BaseException;
import com.akai.common.enums.UserStatus;
import com.akai.system.domain.LoginUser;
import com.akai.system.domain.SysUser;
import com.akai.system.mapper.SysUserMapper;
import com.akai.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysUserService userService;
    /*封装用户信息*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Logger logger = LoggerFactory.getLogger(SysUserDetailsServiceImpl.class);
        /*根据用户名查询信息v1*/
        /*QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        SysUser user = userMapper.selectOne(queryWrapper);*/
        /*根据用户名查询信息v2*/
        SysUser user = userService.selectUserByUserName(username);
        /*查不到数据，抛出异常*/
        if (user == null) {
            logger.info("登录用户: {} 不存在", username);
            throw new UsernameNotFoundException("用户名不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            logger.info("登录用户: {} 已被删除", username);
            throw new BaseException("对不起，您的账号: " + username + " 已被被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            logger.info("登录用户: {} 已被停用", username);
            throw new BaseException("对不起，您的账号: " + username + " 已被被停用");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user);
    }
}
