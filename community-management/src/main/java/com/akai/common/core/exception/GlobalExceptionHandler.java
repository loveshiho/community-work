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
}
