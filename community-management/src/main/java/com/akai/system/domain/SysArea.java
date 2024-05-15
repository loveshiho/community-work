package com.akai.system.domain;

import java.io.Serializable;

public class SysArea implements Serializable {
    private static final long serialVersionUID = 924866255834312759L;
    /**
     * 城市编码
     */
    private Integer code;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 城市父ID
     */
    private Integer parentId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}