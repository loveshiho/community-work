package com.akai.community.service;

import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;

import java.util.List;

public interface HjyCommunityService {
    List<HjyCommunityDto> selectHjyCommunityList(HjyCommunity hjyCommunity);
}
