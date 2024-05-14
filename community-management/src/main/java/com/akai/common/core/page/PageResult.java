package com.akai.common.core.page;

import java.io.Serializable;
import java.util.List;

public class  PageResult implements Serializable {
    // 总记录数
    private Long total;
    // 返回的数据
    private List<?> rows;
    // 状态码
    private Integer code;
    // 响应消息
    private String message;

    public PageResult() {
    }

    public PageResult(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
