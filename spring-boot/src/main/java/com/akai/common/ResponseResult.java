package com.akai.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResult<T> {
    private String status;
    private String message;
    private T data;

    /*我觉得哥们代码写的是有点优雅的*/
    /*成功状态封装*/
    /*无参数*/
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    /*有参数*/
    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder().data(data)
                .message(ResponseStatus.SUCCESS.getDescription())
                .status(ResponseStatus.SUCCESS.getResponseCode())
                .build();
    }

    /*失败状态封装*/
    /*无参数*/
    public static <T> ResponseResult<T> fail(String message) {
        return fail(null, message);
    }

    /*有参数*/
    public static <T> ResponseResult<T> fail(T data, String message) {
        return ResponseResult.<T>builder().data(data)
                .message(ResponseStatus.FAIL.getDescription())
                .status(ResponseStatus.FAIL.getResponseCode())
                .build();
    }
}
