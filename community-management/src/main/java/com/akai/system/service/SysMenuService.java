package com.akai.system.service;

import com.akai.system.domain.SysMenu;
import com.akai.system.domain.vo.RouterVo;

import java.util.List;

public interface SysMenuService {
    public List<SysMenu> selectMenuTreeByUserId(Long userId);
    /*构建前端路由所需要的菜单*/
    public List<RouterVo> buildMenus(List<SysMenu> menus);
}
