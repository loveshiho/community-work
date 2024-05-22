package com.akai.web.controller.system;

import com.akai.common.constant.UserConstants;
import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.common.utils.SecurityUtils;
import com.akai.system.domain.SysDept;
import com.akai.system.domain.TreeSelect;
import com.akai.system.service.SysDeptService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("system/dept")
public class SysDeptController extends BaseController {
    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("/{deptId}")
    public BaseResponse getInfo(@PathVariable Long deptId) {
        return BaseResponse.success(sysDeptService.selectDeptById(deptId));
    }

    @PostMapping
    public BaseResponse add(@RequestBody SysDept dept) {
        if (sysDeptService.checkDeptNameUnique(dept).equals(UserConstants.NOT_UNIQUE))
            return BaseResponse.fail("部门存在，不能添加");
        dept.setCreateBy(SecurityUtils.getUserName());
        return toAjax(sysDeptService.insertDept(dept));
    }

    @PutMapping
    public BaseResponse edit(@RequestBody SysDept dept) {
        if (sysDeptService.checkDeptNameUnique(dept).equals(UserConstants.NOT_UNIQUE))
            return BaseResponse.fail("部门存在，不能修改");
        if (dept.getParentId() == dept.getDeptId()) return BaseResponse.fail("修改失败");
        dept.setUpdateBy(SecurityUtils.getUserName());
        return toAjax(sysDeptService.updateDept(dept));
    }

    @DeleteMapping
    public BaseResponse del(Long deptId) {
        if (sysDeptService.hasChildByDeptId(deptId)) return BaseResponse.fail("存在部门，不能删除");
        if (sysDeptService.checkDeptExistUser(deptId)) return BaseResponse.fail("存在用户，不能删除");
        return toAjax(sysDeptService.deleteDeptById(deptId));
    }

    @RequestMapping("/tree")
    public BaseResponse treeSelect(@RequestBody SysDept sysDept) {
        List<SysDept> sysDepts = sysDeptService.selectDeptList(sysDept);
        List<TreeSelect> list = sysDeptService.buildDeptTreeSelect(sysDepts);
        return BaseResponse.success(list);
    }

    // 查询部门列表，排除当前节点
    @RequestMapping("/list/exclude/{deptId}")
    public BaseResponse excludeChildren(@PathVariable Long deptId) {
        List<SysDept> sysDepts = sysDeptService.selectDeptList(new SysDept());
        List<SysDept> res = new ArrayList<>();
        for (SysDept sysDept : sysDepts) {
            if (sysDept.getDeptId() != deptId && !ArrayUtils.contains(StringUtils.split(sysDept.getAncestors(), ","), deptId + "")) {
                res.add(sysDept);
            }
        }
        return BaseResponse.success(res);
    }
}
