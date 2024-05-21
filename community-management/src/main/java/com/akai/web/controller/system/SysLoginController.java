package com.akai.web.controller.system;

import com.akai.common.core.domain.BaseResponse;
import com.akai.common.utils.ChainedMap;
import com.akai.common.utils.ServletUtils;
import com.akai.framework.service.SysPermissionService;
import com.akai.system.domain.LoginUser;
import com.akai.system.domain.SysMenu;
import com.akai.system.domain.SysUser;
import com.akai.system.domain.vo.LoginUserVo;
import com.akai.system.domain.vo.RouterVo;
import com.akai.system.service.SysLoginService;
import com.akai.system.service.SysMenuService;
import com.akai.system.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService sysLoginService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/login")
    public BaseResponse login(@RequestBody LoginUserVo loginUserVo) {
        String token = sysLoginService.login(loginUserVo.getUserName(), loginUserVo.getPassword(), loginUserVo.getCode(), loginUserVo.getUuid());
        return BaseResponse.success(ChainedMap.create().set("token", token));
    }

    @RequestMapping("/getInfo")
    public BaseResponse getInfo() {
        /*获取用户信息*/
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser sysUser = loginUser.getSysUser();
        /*角色集合*/
        List<String> rolePermission = sysPermissionService.getRolePermission(sysUser);
        List<String> menuPermission = sysPermissionService.getMenuPermission(sysUser);

        return BaseResponse
                .success(ChainedMap.create()
                        .set("user", sysUser)
                        .set("roles", rolePermission).set("permissions", menuPermission));
    }

    @RequestMapping("/getRouters")
    public BaseResponse getRouters() {
        /*获取用户信息*/
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser sysUser = loginUser.getSysUser();
        List<SysMenu> sysMenus = sysMenuService.selectMenuTreeByUserId(sysUser.getUserId());
        return BaseResponse
                .success(ChainedMap.create()
                        .set("info", sysMenus));
    }

    @RequestMapping("/getRoutersVo")
    public BaseResponse getRoutersVo() {
        /*获取用户信息*/
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser sysUser = loginUser.getSysUser();
        /*获取菜单列表*/
        List<SysMenu> sysMenus = sysMenuService.selectMenuTreeByUserId(sysUser.getUserId());
        /*转换为前端需要的路由列表*/
        List<RouterVo> routerVos = sysMenuService.buildMenus(sysMenus);
        return BaseResponse
                .success(ChainedMap.create()
                        .set("info", routerVos));
    }
}
