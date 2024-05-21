package com.akai.framework.service;

import com.akai.system.domain.SysUser;
import com.akai.system.mapper.SysMenuMapper;
import com.akai.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
/*用户权限处理service*/
public class SysPermissionService {
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysMenuMapper menuMapper;

    public List<String> getRolePermission(SysUser user) {
        List<String> roles = new ArrayList<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            List<String> list = roleMapper.selectRolePermissionByUserId(user.getUserId());
            roles.addAll(list);
        }
        return roles;
    }

    public List<String> getMenuPermission(SysUser user) {
        List<String> perms = new ArrayList<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            List<String> list = menuMapper.selectMenuPermsByUserId(user.getUserId());
            perms.addAll(list);
        }
        return perms;
    }
}
