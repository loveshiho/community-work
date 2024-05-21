package com.akai.common.utils;

import com.akai.system.domain.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    /*获取用户名*/
    public static String getUserName() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginUser.getUsername();
    }

    /*获取用户信息*/
    public static LoginUser getLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
