package com.akai.web.controller.community;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.community.domain.HjyCommunity;
import com.akai.community.domain.dto.HjyCommunityDto;
import com.akai.community.domain.dto.HjyCommunityExportDto;
import com.akai.community.service.HjyCommunityService;
import com.akai.community.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exportExcel")
public class ExportExcelController extends BaseController {
    @Autowired
    private HjyCommunityService hjyCommunityService;

    @RequestMapping("/community")
    public BaseResponse getExcel(HttpServletResponse response, HjyCommunity hjyCommunity) throws Exception{
        /*分页查询导出*/
        startPage();
        List<HjyCommunityDto> list = hjyCommunityService.selectHjyCommunityList(hjyCommunity);
        List<HjyCommunityExportDto> exportDtoList = new ArrayList<>();
        list.forEach(item -> {
            HjyCommunityExportDto hjyCommunityExportDto = new HjyCommunityExportDto();
            hjyCommunityExportDto.setCommunityId(item.getCommunityId());
            hjyCommunityExportDto.setCommunityCode(item.getCommunityCode());
            hjyCommunityExportDto.setCommunityName(item.getCommunityName());
            hjyCommunityExportDto.setCommunityCityName(item.getCommunityCityName());
            hjyCommunityExportDto.setCommunityProvinceName(item.getCommunityProvinceName());
            hjyCommunityExportDto.setCommunityTownName(item.getCommunityTownName());
            hjyCommunityExportDto.setRemark(item.getRemark());
            hjyCommunityExportDto.setCreateTime(item.getCreateTime());
            exportDtoList.add(hjyCommunityExportDto);
        });
        ExcelUtils.exportExcel(exportDtoList, HjyCommunityExportDto.class, "小区信息.xlsx", response, new ExportParams("小区信息列表", "小区信息"));

        return BaseResponse.success("导出成功");
    }
}
