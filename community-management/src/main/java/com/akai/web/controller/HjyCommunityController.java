package com.akai.web.controller;

import com.akai.common.constant.HttpStatus;
import com.akai.common.core.page.PageResult;
import com.akai.common.utils.ServletUtils;
import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;
import com.akai.community.service.HjyCommunityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/community")
public class HjyCommunityController {
    @Autowired
    private HjyCommunityService hjyCommunityService;

    @RequestMapping("/list")
    public PageResult list(HjyCommunity hjyCommunity) {
        Integer pageSize = ServletUtils.getParameterToInt("pageSize");
        Integer pageNum = ServletUtils.getParameterToInt("pageNum");
        // 使用 PageHelper
        PageHelper.startPage(pageNum, pageSize);
        List<HjyCommunityDto> list = hjyCommunityService.selectHjyCommunityList(hjyCommunity);
        // 封装数据
        PageResult result = new PageResult(new PageInfo<>(list).getTotal(), list);
        result.setCode(HttpStatus.SUCCESS);
        result.setMessage("查询成功");

        // 响应结果
        return result;
    }
}
