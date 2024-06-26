package com.akai.common.core.page;

public class PageDomain {
    /* 当前记录起始索引 */
    private Integer pageNum;
    /* 每页显示记录数 */
    private Integer pageSize;

    public PageDomain(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
