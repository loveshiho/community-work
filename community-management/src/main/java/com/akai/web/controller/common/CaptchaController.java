package com.akai.web.controller.common;

import com.akai.common.core.domain.BaseResponse;
import com.akai.common.utils.ChainedMap;
import com.akai.common.utils.Constants;
import com.akai.common.utils.RedisCache;
import com.akai.common.utils.UUIDUtils;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {
    @Autowired
    private RedisCache redisCache;

    /*给前端返回验证码*/
    @RequestMapping("/captchaImage")
    public BaseResponse<ChainedMap> getCode() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        /*生成验证码，及验证码唯一标识*/
        String uuid = UUIDUtils.simpleUUID();
        String redisKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String code = specCaptcha.text().toLowerCase();
        /*保存到redis*/
        redisCache.setCacheObject(redisKey, code, 600, TimeUnit.SECONDS);
        return BaseResponse.success(ChainedMap
                .create()
                .set("uuid", uuid)
                .set("img", specCaptcha.toBase64()));
    }
}
