package com.akai.system.service.impl;

import com.akai.system.domain.SysDept;
import com.akai.system.domain.SysRole;
import com.akai.system.domain.SysUser;
import com.akai.system.mapper.SysDeptMapper;
import com.akai.system.mapper.SysRoleMapper;
import com.akai.system.mapper.SysUserMapper;
import com.akai.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public SysUser selectUserByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null) return null;
        SysDept sysDept = sysDeptMapper.selectDeptById(sysUser.getDeptId());
        List<Long> roleIds = sysRoleMapper.selectRoleIdByUserId(sysUser.getUserId());
        List<SysRole> roles = sysRoleMapper.selectBatchIds(roleIds);
        Long[] longArray = roleIds.toArray(new Long[0]);

        sysUser.setDept(sysDept);
        sysUser.setRoles(roles);
        sysUser.setRoleIds(longArray);
        return sysUser;
    }
}
