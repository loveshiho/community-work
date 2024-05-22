package com.akai.web.controller.system;

import com.akai.common.constant.UserConstants;
import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.common.core.page.PageResult;
import com.akai.system.domain.SysDictType;
import com.akai.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private SysDictTypeService dictTypeService;

    /*查询字典数据列表*/
    @RequestMapping("/list")
    public PageResult list(SysDictType sysDictType) {
        startPage();
        List<SysDictType> sysDictTypes = dictTypeService.selectDictTypeList(sysDictType);
        return getData(sysDictTypes);
    }

    /*根据Id查询字典类型详细信息*/
    @RequestMapping("/{dictId}")
    public BaseResponse getInfo(@PathVariable Long dictId) {
        SysDictType sysDictType = dictTypeService.selectDictTypeById(dictId);
        return BaseResponse.success(sysDictType);
    }

    /*新增字典类型*/
    @PostMapping
    public BaseResponse add(SysDictType sysDictType) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(sysDictType))) {
            return BaseResponse.fail("新增失败");
        }
        int rows = dictTypeService.insertDictType(sysDictType);
        return toAjax(rows);
    }

    /*修改字典类型*/
    @PutMapping
    public BaseResponse edit(SysDictType sysDictType) {
        int rows = dictTypeService.updateDictType(sysDictType);
        return toAjax(rows);
    }

    /*删除字典类型*/
    @DeleteMapping("/{dictIds}")
    public BaseResponse del(Long[] dictIds) {
        int rows = dictTypeService.deleteDictTypeByIds(dictIds);
        return toAjax(rows);
    }
}
