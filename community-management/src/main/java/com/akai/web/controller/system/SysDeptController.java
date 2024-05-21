package com.akai.web.controller.system;

import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.system.domain.SysDept;
import com.akai.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("system/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private SysDeptService sysDeptService;

    /*获取部门列表*/
    @PreAuthorize("@pe.hasPerm('system:dept:list')")
    @GetMapping("/list")
    public BaseResponse list() {
        List<SysDept> sysDepts = sysDeptService.selectDeptList();
        return BaseResponse.success(sysDepts);
    }
}
