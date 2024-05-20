package com.akai.web.controller.system;

import com.akai.common.core.domain.BaseResponse;
import com.akai.common.utils.ChainedMap;
import com.akai.system.domain.vo.LoginUserVo;
import com.akai.system.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService sysLoginService;
    @RequestMapping("/login")

    public BaseResponse login(@RequestBody LoginUserVo loginUserVo) {
        String token = sysLoginService.login(loginUserVo.getUserName(), loginUserVo.getPassword(), loginUserVo.getCode(), loginUserVo.getUuid());
        return BaseResponse.success(ChainedMap.create().set("token", token));
    }
}
