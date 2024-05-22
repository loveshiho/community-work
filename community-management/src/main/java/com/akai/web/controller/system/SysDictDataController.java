package com.akai.web.controller.system;

import com.akai.common.core.controller.BaseController;
import com.akai.common.core.domain.BaseResponse;
import com.akai.common.core.page.PageResult;
import com.akai.common.utils.SecurityUtils;
import com.akai.system.domain.SysDictData;
import com.akai.system.service.SysDictDataService;
import com.akai.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/dict")
public class SysDictDataController extends BaseController {
    @Autowired
    private SysDictDataService sysDictDataService;
    @Autowired
    private SysDictTypeService sysDictTypeService;

    /*查询字典数据列表*/
    @RequestMapping("/list")
    public PageResult list(SysDictData sysDictData) {
        startPage();
        List<SysDictData> list = sysDictDataService.selectDictDataList(sysDictData);
        return getData(list);
    }

    /*根据字典Id查询字典详细信息*/
    @RequestMapping("/{dictCode}")
    public BaseResponse getInfo(@PathVariable Long dictCode) {
        return BaseResponse.success(sysDictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping("/type/{dictType}")
    public BaseResponse getDictByType(@PathVariable String dictType) {
        return BaseResponse.success(sysDictTypeService.selectDictDataByType(dictType));
    }

    @PostMapping
    public BaseResponse add(@RequestBody SysDictData dictData) {
        dictData.setCreateBy(SecurityUtils.getUserName());
        int rows = sysDictDataService.insertDictData(dictData);
        return toAjax(rows);
    }

    /**
     * 修改字典类型
     */
    @PutMapping
    public BaseResponse edit(@RequestBody SysDictData dictData) {
        dictData.setCreateBy(SecurityUtils.getUserName());
        int rows = sysDictDataService.updateDictData(dictData);
        return toAjax(rows);
    }

    /**
     * 删除字典类型
     */
    @DeleteMapping("/{dictCodes}")
    public BaseResponse del(@PathVariable Long[] dictCodes) {
        int rows = sysDictDataService.deleteDictDataByIds(dictCodes);
        return toAjax(rows);
    }

    /*清空缓存*/
    @DeleteMapping("/clearCache")
    public BaseResponse clear() {
        sysDictTypeService.clearCache();
        return BaseResponse.success(null);
    }
}
