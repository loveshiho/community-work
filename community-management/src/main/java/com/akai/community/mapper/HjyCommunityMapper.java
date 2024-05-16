package com.akai.community.mapper;

import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HjyCommunityMapper extends BaseMapper<HjyCommunity> {
    @Select("<script>SELECT \n" +
            "    *,\n" +
            "    s1.`name` AS communityProvinceName,\n" +
            "    s2.`name` AS communityCityName,\n" +
            "    s3.`name` AS communityTownName\n" +
            "FROM hjy_community hc \n" +
            "LEFT JOIN sys_area s1 ON hc.`community_province_code` = s1.`code`\n" +
            "LEFT JOIN sys_area s2 ON hc.`community_city_code` = s2.`code`\n" +
            "LEFT JOIN sys_area s3 ON hc.`community_town_code` = s3.`code`" +
            "<where>" +
            "<if test=\"communityName !=null and communityName != ''\">" +
            "hc.community_name like concat('%',#{communityName},'%')" +
            "</if> " +

            "<if test=\"communityCode !=null and communityCode != ''\">" +
            "and hc.community_code = #{communityCode}" +
            "</if> " +
            "</where>" +
            "</script>")
    /*这一方法考虑了条件查询，既可查询所有，也可按条件查询，不给条件即是查询所有*/
    List<HjyCommunityDto> queryList(HjyCommunity hjyCommunity);
}
