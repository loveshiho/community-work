package com.akai.mapper;

import com.akai.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    // 根据用户Id获取用户权限信息
    @Select("select distinct sm.perms\n" +
            "from sys_menu sm\n" +
            "         left join sys_role_menu srm on sm.menu_id = srm.menu_id\n" +
            "         left join sys_user_role sur on srm.role_id = sur.role_id\n" +
            "where sur.user_id = #{id} and sm.status = 0;")
    List<String> selectPermsByUserId(Long id);
}
