package com.akai.system.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.akai.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

public class SysPost extends BaseEntity {
    /**
     * 岗位序号
     */
    @Excel(name = "岗位序号")
    @TableId
    private Long postId;

    /**
     * 岗位编码
     */
    @Excel(name = "岗位编码")
    private String postCode;

    /**
     * 岗位名称
     */
    @Excel(name = "岗位名称")
    private String postName;

    /**
     * 岗位排序
     */
    @Excel(name = "岗位排序")
    private String postSort;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    private boolean flag = false;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostSort() {
        return postSort;
    }

    public void setPostSort(String postSort) {
        this.postSort = postSort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public SysPost(Long postId, String postCode, String postName, String postSort, String status, boolean flag) {
        this.postId = postId;
        this.postCode = postCode;
        this.postName = postName;
        this.postSort = postSort;
        this.status = status;
        this.flag = flag;
    }

    public SysPost() {
    }

    @Override
    public String toString() {
        return "SysPost{" +
                "postId=" + postId +
                ", postCode='" + postCode + '\'' +
                ", postName='" + postName + '\'' +
                ", postSort='" + postSort + '\'' +
                ", status='" + status + '\'' +
                ", flag=" + flag +
                '}';
    }
}
