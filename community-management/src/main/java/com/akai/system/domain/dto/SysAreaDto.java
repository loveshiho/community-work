package com.akai.system.domain.dto;

import java.io.Serializable;
import java.util.List;

// AreaDto 构建区域信息层级，返回 dto给前端
public class SysAreaDto implements Serializable {
    // 城市编码
    private Integer code;
    // 城市名称
    private String name;
    // 子区域
    private List<SysAreaDto> children;

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

    public List<SysAreaDto> getChildren() {
        return children;
    }

    public void setChildren(List<SysAreaDto> children) {
        this.children = children;
    }
}
