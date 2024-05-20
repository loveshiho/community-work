package com.akai.common.core.exception;

import com.akai.common.constant.HttpStatus;

/*业务异常基类*/
/*RuntimeException类不可拓展，即不能使用泛型类，设置为String，手动序列化data*/
public class CustomException extends RuntimeException {
    private int code;
    private boolean success;
    private String msg;

    public CustomException() {
    }

    public CustomException(int code, String msg) {
        this.code = code;
        this.success = String.valueOf(code).equals(HttpStatus.SUCCESS);
        this.msg = msg;
    }

    public CustomException(int code, boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
