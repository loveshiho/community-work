package com.akai.common.core.exception;

public class BaseException extends RuntimeException{
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException() {
    }

    public BaseException(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
