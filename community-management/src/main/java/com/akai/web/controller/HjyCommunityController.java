package com.akai.web.controller;

import com.akai.common.core.controller.BaseController;
import com.akai.common.core.page.PageResult;

import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;
import com.akai.community.service.HjyCommunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/community")
public class HjyCommunityController extends BaseController {
    @Autowired
    private HjyCommunityService hjyCommunityService;

    @RequestMapping("/list")
    public PageResult list(HjyCommunity hjyCommunity) {
        // 使用 PageHelper
        startPage();
        List<HjyCommunityDto> list = hjyCommunityService.selectHjyCommunityList(hjyCommunity);
        // 封装数据
        PageResult result = getData(list);
        // 响应结果
        return result;
    }
}
