package com.akai.system.mapper;

import com.akai.system.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    /*根据用户ID 查询角色*/
    @Select("select distinct sr.role_key\n" +
            "from sys_role sr\n" +
            "left join sys_user_role sur on sr.role_id = sur.role_id\n" +
            "where sur.user_id = #{userId} and sr.status = 0;")
    public List<String> selectRolePermissionByUserId(Long userId);
    /*根据用户ID 查询角色Id*/
    @Select("select distinct sr.role_id\n" +
            "from sys_role sr\n" +
            "left join sys_user_role sur on sr.role_id = sur.role_id\n" +
            "where sur.user_id = #{userId} and sr.status = 0;")
    public List<Long> selectRoleIdByUserId(Long userId);
}
