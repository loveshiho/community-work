package com.akai.web.controller.system;

import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.system.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/area")
public class SysAreaController extends BaseController {
    @Autowired
    private SysAreaService sysAreaService;

    @RequestMapping("/tree")
    public BaseResponse getAreaTree() {
        return BaseResponse.success(sysAreaService.findAreaAsTree());
    }

}
