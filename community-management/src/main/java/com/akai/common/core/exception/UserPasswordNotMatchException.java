package com.akai.common.core.exception;

public class UserPasswordNotMatchException extends CustomException {
    public UserPasswordNotMatchException() {
        super(400, false, "密码错误");
    }
}
