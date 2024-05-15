package com.akai.system.domain;

import java.io.Serializable;

public class SysArea implements Serializable {
    private static final long serialVersionUID = 924866255834312759L;
    private Integer code;
    private String name;
    private Integer parentId;

    public SysArea() {
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
