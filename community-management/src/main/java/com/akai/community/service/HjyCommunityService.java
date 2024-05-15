package com.akai.community.service;

import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;

import java.util.List;

public interface HjyCommunityService {
    List<HjyCommunityDto> selectHjyCommunityList(HjyCommunity hjyCommunity);
    // 新增小区
    int insertHjyCommunity(HjyCommunity hjyCommunity);
    // 查询小区
    HjyCommunity selectHjyCommunityById(Long communityId);
    // 修改小区
    int updateHjyCommunity(HjyCommunity hjyCommunity);
}