package com.akai.community.service.impl;

import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;
import com.akai.community.mapper.HjyCommunityMapper;
import com.akai.community.service.HjyCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HjyCommunityServiceImpl implements HjyCommunityService {
    @Autowired
    private HjyCommunityMapper hjyCommunityMapper;
    private static final String CODE_PREFIX = "COMMUNITY_";

    @Override
    public List<HjyCommunityDto> selectHjyCommunityList(HjyCommunity hjyCommunity) {
        return hjyCommunityMapper.queryList(hjyCommunity);
    }

    @Override
    public int insertHjyCommunity(HjyCommunity hjyCommunity) {
        // 设置小区编码
        hjyCommunity.setCommunityCode(CODE_PREFIX + System.currentTimeMillis());
        return hjyCommunityMapper.insert(hjyCommunity);
    }
}
