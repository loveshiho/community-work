package com.akai.controller;

import com.akai.common.Constants;
import com.akai.common.ResponseResult;
import com.akai.utils.RedisCache;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {
    @Autowired
    private RedisCache redisCache;
    /*给前端返回验证码*/
    @RequestMapping("/captchaImage")
    public ResponseResult getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        /*生成验证码，及验证码唯一标识*/
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String key = Constants.CAPTCHA_CODE_KEY + uuid;
        String code = specCaptcha.text().toLowerCase();
        /*保存到redis*/
        redisCache.setCacheObject(key, code, 600, TimeUnit.SECONDS);
        /*创建map，封装响应结果*/
        HashMap<String, Object> result = new HashMap<>();
        result.put("uuid", uuid);
        result.put("img", specCaptcha.toBase64());
        return new ResponseResult("200", "验证码获取成功", result);
    }
}
