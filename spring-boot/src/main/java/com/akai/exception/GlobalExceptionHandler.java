package com.akai.exception;

import com.akai.common.ResponseResult;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    /*对400做出响应*/
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseResult handleParameterVerificationException(@NotNull Exception e) {
        List<String> res = new ArrayList<>();
        log.warn("Exception: {}", e.getMessage());
        if (e instanceof BindException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            bindingResult.getAllErrors().forEach(error -> {
                /*FieldError extends ObjectError*/
                FieldError fieldError = (FieldError) error;
                /*object 对象，field 属性*/
                log.error("Invalid Parameter : object - {},field - {},errorMessage - {}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                res.add(fieldError.getDefaultMessage());
            });
        } else {
            log.error("invalid parameter");
            res.add("invalid parameter");
        }
        return ResponseResult.fail(res, "invalid parameter");
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseResult processBusinessException(BusinessException businessException) {
        log.error(businessException.getLocalizedMessage(), businessException);
        // 这里可以屏蔽掉后台的异常栈信息，直接返回"business error"
        return ResponseResult.fail(businessException.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult processException(Exception exception) {
        log.error(exception.getLocalizedMessage());
        // 这里可以屏蔽掉后台的异常栈信息，直接返回"server error"
        return ResponseResult.fail(exception.getLocalizedMessage());
    }
}
