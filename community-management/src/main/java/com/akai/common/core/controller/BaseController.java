package com.akai.common.core.controller;

import com.akai.common.constant.HttpStatus;
import com.akai.common.core.domain.BaseResponse;
import com.akai.common.core.page.PageDomain;
import com.akai.common.core.page.PageResult;
import com.akai.common.utils.ServletUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class BaseController {
    // 使用静态常量，符合规范
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    /*封装分页数据*/
    public static PageDomain getPageDomain() {
        Integer pageNum = ServletUtils.getParameterToInt(PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(PAGE_SIZE);
        return new PageDomain(pageNum, pageSize);
    }
    /*调用pagehelper*/
    protected void startPage() {
        PageDomain pageDomain = getPageDomain();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }
    /*响应分页数据*/
    /*封装查询结果*/
    protected PageResult getData(List<?> list) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(HttpStatus.SUCCESS);
        pageResult.setMessage("分页查询成功");
        pageResult.setRows(list);
        pageResult.setTotal(new PageInfo<>(list).getTotal());
        return pageResult;
    }
    /*响应返回结果*/
    protected BaseResponse toAjax(int rows) {
        return rows > 0 ? BaseResponse.success(rows) : BaseResponse.fail("操作失败");
    }
}
