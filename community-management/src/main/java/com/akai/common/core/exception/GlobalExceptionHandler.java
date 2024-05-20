package com.akai.common.core.exception;

import com.akai.common.core.domain.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public BaseResponse baseExceptionHandler(BaseException e) {
        return BaseResponse.fail(e.getDefaultMessage());
    }
    /*配置全局异常处理*/
    @ExceptionHandler(CustomException.class)
    public BaseResponse businessException(CustomException e) {
        return BaseResponse.fail(String.valueOf(e.getCode()), e.getMsg(), e.isSuccess());
    }
}
