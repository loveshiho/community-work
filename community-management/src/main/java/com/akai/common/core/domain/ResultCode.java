package com.akai.common.core.domain;

// 返回响应状态码
public enum ResultCode {
    SUCCESS("200", "操作成功"),
    ERROR("500", "操作失败");
    // 自定义状态码
    private String code;
    // 自定义描述
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
