package com.akai.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginUser implements UserDetails {

    private SysUser sysUser;

    private List<String> permissions;

    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LoginUser(SysUser sysUser, List<String> permissions, List<String> roles) {
        this.sysUser = sysUser;
        this.permissions = permissions;
        this.roles = roles;
    }

    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public LoginUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public LoginUser() {
    }

    /**
     * 用于获取用户被授予的权限，可以用于实现访问控制
     */
    @JSONField(serialize = false)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // authorities权限集合
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        /*spring security需要的权限集合*/
        /*将 permissions集合中的 String类型的权限信息转换成 SimpleGrantedAuthority*/
        permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        return authorities;
    }

    /**
     * 用于获取用户的密码，一般用于进行密码验证
     */
    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    /**
     * 用于获取用户名，一般用于进行身份验证
     */
    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    /**
     * 用于判断用户的账户是否未过期，可以用于实现账户有效期控制
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用于判断用户的账户是否未锁定，可以用于实现账户锁定功能
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用于判断用户的凭证（如密码）是否未过期，可以用于实现密码有效期控制
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用于判断用户是否已激活，可以用于实现账户激活功能
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "sysUser=" + sysUser +
                ", permissions=" + permissions +
                '}';
    }

}
