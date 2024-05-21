package com.akai.system.service.impl;

import com.akai.common.constant.UserConstants;
import com.akai.system.domain.SysMenu;
import com.akai.system.domain.vo.MetaVo;
import com.akai.system.domain.vo.RouterVo;
import com.akai.system.mapper.SysMenuMapper;
import com.akai.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> sysMenus = null;
        if (userId == 1l) {
            sysMenus = sysMenuMapper.selectMenuTreeAll();
        } else {
            sysMenus = sysMenuMapper.selectMenuTreeByUserId(userId);
        }
        if (sysMenus != null) {
            /*获取完整的树*/
            for (int i = 0; i < sysMenus.size(); i++) {
                SysMenu sysMenu = sysMenus.get(i);
                if (sysMenu.getParentId() > 0) break;
                sysMenu.setChildren(getChildrenAsTree(sysMenu.getMenuId(), i, sysMenus));
            }
        }
        return sysMenus;
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routerVos = new ArrayList<>();
        menus.forEach(menu -> {
            RouterVo routerVo = new RouterVo();
            routerVo.setName(getRouterVoName(menu));
            routerVo.setPath(getRouterPath(menu));
            routerVo.setComponent(getRouterVoComponent(menu));
            routerVo.setHidden("1".equals(menu.getVisible()));
            routerVo.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), "1".equals(menu.getIsCache())));
            List<SysMenu> children = menu.getChildren();
            if (children != null && children.size() > 0 && menu.getMenuType().equals(UserConstants.TYPE_DIR)) {
                routerVo.setAlwaysShow(true); // 下面有子路由
                routerVo.setRedirect("noRedirect"); // 在导航栏中可点击，不能跳转
                routerVo.setChildren(buildMenus(children)); // 递归设置子菜单
            }
            routerVos.add(routerVo);
        });
        return routerVos;
    }

    /*获取路由名称*/
    private String getRouterVoName(SysMenu menu) {
        String path = menu.getPath();
        if (path == null || path == "") {
            return path;
        }
        return Character.toUpperCase(path.charAt(0)) + path.substring(1);
    }

    /*获取路由地址*/
    private String getRouterPath(SysMenu menu) {
        String path = menu.getPath();
        if (menu.getParentId() == 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
            path = "/" + path;
        }
        return path;
    }

    /*获取组件信息*/
    private String getRouterVoComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (menu.getComponent() != null) {
            component = menu.getComponent();
        } else if (menu.getParentId() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    private List<SysMenu> getChildrenAsTree(Long menuId, int index, List<SysMenu> sysMenus) {
        List<SysMenu> list = new ArrayList<>();
        for (int i = index + 1; i < sysMenus.size(); i++) {
            SysMenu sysMenu = sysMenus.get(i);
            if (sysMenu.getParentId() == menuId) {
                sysMenu.setChildren(getChildrenAsTree(sysMenu.getMenuId(), i, sysMenus));
                list.add(sysMenu);
            }
        }
        return list;
    }
}
