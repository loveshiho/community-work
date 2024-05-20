package com.akai.common.core.exception;

public class CaptchaNotMatchException extends CustomException{
    public CaptchaNotMatchException() {
        super(400, false, "验证码错误");
    }
}
