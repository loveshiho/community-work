package com.akai.system.domain.dto;

import java.io.Serializable;
import java.util.List;

public class SysAreaDto implements Serializable {
    private Integer code;
    private String name;
    private List<SysAreaDto> children;

    public SysAreaDto() {
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

    public List<SysAreaDto> getChildren() {
        return this.children;
    }

    public void setChildren(List<SysAreaDto> children) {
        this.children = children;
    }
}
