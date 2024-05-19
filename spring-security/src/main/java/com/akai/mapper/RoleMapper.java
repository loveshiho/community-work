package com.akai.mapper;

import com.akai.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    @Select("select sr.role_key\n" +
            "from sys_role sr\n" +
            "left join sys_user_role sur on sr.role_id = sur.role_id\n" +
            "where sur.user_id = #{id} and sr.status = 0;")
    List<String> selectRolesByUserId(Long id);
}
