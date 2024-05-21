package com.akai.web.controller.community;

import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.common.core.page.PageResult;

import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;
import com.akai.community.service.HjyCommunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class HjyCommunityController extends BaseController {
    @Autowired
    private HjyCommunityService hjyCommunityService;

    @RequestMapping("/list")
    @PreAuthorize("@pe.hasPerm('system:community:list')")
    public PageResult list(HjyCommunity hjyCommunity) {
        // 使用 PageHelper
        startPage();
        List<HjyCommunityDto> list = hjyCommunityService.selectHjyCommunityList(hjyCommunity);
        // 封装数据
        PageResult result = getData(list);
        // 响应结果
        return result;
    }

    @PostMapping("/")
    public BaseResponse add(@RequestBody HjyCommunity hjyCommunity) {
        return toAjax(hjyCommunityService.insertHjyCommunity(hjyCommunity));
    }

    @GetMapping("/{communityId}")
    public BaseResponse getInfo(@PathVariable("communityId") Long communityId) {
        return BaseResponse.success(hjyCommunityService.selectHjyCommunityById(communityId));
    }

    @PutMapping
    public BaseResponse edit(@RequestBody HjyCommunity hjyCommunity) {
        return toAjax(hjyCommunityService.updateHjyCommunity(hjyCommunity));
    }

    @DeleteMapping("/{communityIds}")
    public BaseResponse delete(@PathVariable("communityIds") Long[] ids) {
        return toAjax(hjyCommunityService.deleteCommunityByIds(ids));
    }

    @GetMapping("/queryPullDown")
    public BaseResponse queryPullDown(HjyCommunity hjyCommunity) {
        return BaseResponse.success(hjyCommunityService.queryPullDown(hjyCommunity));
    }

}
