package com.akai.framework.security.sevice;

import com.akai.common.utils.ServletUtils;
import com.akai.system.domain.LoginUser;
import com.akai.system.domain.SysRole;
import com.akai.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*自定义权限校验*/
@Component("pe")
public class PermsExpressionService {
    /*设置admin用户权限常量*/
    private static final String ALL_PERMISSION = "*:*:*";
    @Autowired
    private TokenService tokenService;

    /*判断用户是否具备某一权限*/
    public boolean hasPerm(String permission) {
        if (permission == null || permission == "") return true;
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser == null || loginUser.getPermissions() == null) return false;
        return hasPermssion(loginUser.getPermissions(), permission);
    }

    /*判断是否包含权限*/
    private boolean hasPermssion(List<String> permissions, String permission) {
        return permissions.contains(permission) || permissions.contains(ALL_PERMISSION);
    }

    /*判断用户是否具有指定权限之一*/
    public boolean hasAnyPerm(String... permissions) {
        if (permissions == null || permissions.length < 1) {
            return true;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser == null || loginUser.getPermissions() == null) return false;

        List<String> authorities = loginUser.getPermissions();
        for (String permission : permissions) {
            if (permission != null && hasPermssion(authorities, permission)) return true;
        }
        return false;
    }

    /*判断用户是否具备某一角色*/
    public boolean hasRole(String role) {
        if (role == null || role == "") return true;
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        /*获取角色信息*/
        List<SysRole> roles = loginUser.getSysUser().getRoles();
        if (loginUser == null || roles == null) return false;
        for (SysRole sysRole : roles) {
            String roleKey = sysRole.getRoleKey();
            if (roleKey.equals("admin") || roleKey.equals(role)) return true;
        }
        return false;
    }

    /*判断用户是否具有指定角色之一*/
    public boolean hasAnyRole(String... roles) {
        if (roles == null || roles.length < 1) return true;
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        /*获取角色信息*/
        List<SysRole> sysRoles = loginUser.getSysUser().getRoles();
        if (loginUser == null || roles == null) return false;
        for (String role : roles) {
            if (role != null && hasRole(role)) return true;
        }
        return false;
    }
}
