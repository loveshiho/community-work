package com.akai.system.mapper;

import com.akai.system.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /*根据用户Id查询权限*/
    @Select("select distinct sm.perms\n" +
            "from sys_menu sm\n" +
            "         left join sys_role_menu srm on sm.menu_id = srm.menu_id\n" +
            "         left join sys_user_role sur on srm.role_id = sur.role_id\n" +
            "where sur.user_id = #{userId}\n" +
            "  and sm.status = 0;")
    public List<String> selectMenuPermsByUserId(Long userId);
    /*查询所有菜单*/
    @Select("select distinct *\n" +
            "from sys_menu sm\n" +
            "where sm.menu_type in ('M', 'C')\n" +
            "  and sm.status = 0 ORDER BY sm.parent_id, sm.order_num;")
    public List<SysMenu> selectMenuTreeAll();
    /*根据用户Id 查询菜单树信息*/
    @Select("select distinct *\n" +
            "from sys_menu sm\n" +
            "         left join sys_role_menu srm on sm.menu_id = srm.menu_id\n" +
            "         left join sys_user_role sur on srm.role_id = sur.role_id\n" +
            "where sur.user_id = #{userId}\n" +
            "and sm.menu_type in ('M', 'C')\n" +
            "  and sm.status = 0 ORDER BY sm.parent_id, sm.order_num;")
    public List<SysMenu> selectMenuTreeByUserId(Long userId);
}
