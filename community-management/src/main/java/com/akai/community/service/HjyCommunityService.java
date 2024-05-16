package com.akai.community.service;

import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;
import com.akai.community.domain.vo.HjyCommunityVo;

import java.util.List;

public interface HjyCommunityService {
    // 查询小区
    List<HjyCommunityDto> selectHjyCommunityList(HjyCommunity hjyCommunity);
    // 新增小区
    int insertHjyCommunity(HjyCommunity hjyCommunity);
    // 查询小区
    HjyCommunity selectHjyCommunityById(Long communityId);
    // 修改小区
    int updateHjyCommunity(HjyCommunity hjyCommunity);
    // 删除小区
    int deleteCommunityByIds(Long[] ids);
    // 获取小区下拉列表
    List<HjyCommunityVo> queryPullDown(HjyCommunity hjyCommunity);
}
