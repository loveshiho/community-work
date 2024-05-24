package com.akai.controller;

import com.akai.common.ResponseResult;
import com.akai.pojo.UserParam;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    /*http://localhost:8080/user/add*/
    @PostMapping("add")
    public ResponseResult add(@Valid @RequestBody UserParam userParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.forEach(error -> {
                /*FieldError extends ObjectError*/
                FieldError fieldError = (FieldError) error;
                /*object 对象，field 属性*/
                log.error("Invalid Parameter : object - {},field - {},errorMessage - {}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseResult.fail("invalid parameter");
        }
        return ResponseResult.success();
    }
    /*http://localhost:8080/user/edit*/
    @PostMapping("edit")
    public ResponseResult edit(@Valid @RequestBody UserParam userParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.forEach(error -> {
                /*FieldError extends ObjectError*/
                FieldError fieldError = (FieldError) error;
                /*object 对象，field 属性*/
                log.error("Invalid Parameter : object - {},field - {},errorMessage - {}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseResult.fail("invalid parameter");
        }
        return ResponseResult.success();
    }

    /*http://localhost:8080/user/exception*/
    @PostMapping("exception")
    public ResponseResult add(@Valid @RequestBody UserParam userParam) {
        return ResponseResult.success();
    }
}
