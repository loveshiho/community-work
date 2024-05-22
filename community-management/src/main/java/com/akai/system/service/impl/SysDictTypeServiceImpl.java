package com.akai.system.service.impl;

import com.akai.common.constant.UserConstants;
import com.akai.system.domain.SysDictData;
import com.akai.system.domain.SysDictType;
import com.akai.system.mapper.SysDictDataMapper;
import com.akai.system.mapper.SysDictTypeMapper;
import com.akai.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    @Override
    public List<SysDictType> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    @Override
    public int deleteDictTypeByIds(Long[] dictIds) {
        return dictTypeMapper.deleteDictTypeByIds(dictIds);
    }

    @Override
    public int insertDictType(SysDictType dictType) {
        return dictTypeMapper.insertDictType(dictType);
    }

    /**
     * 修改字典类型信息
     *
     * @param dictType
     * @return: int
     */
    @Override
    @Transactional  // 事务注解
    public int updateDictType(SysDictType dictType) {
        /*两张表的dictType关联，修改字典数据表的字典类型*/
        SysDictType oldDictType = dictTypeMapper.selectDictTypeById(dictType.getDictId());
        dictDataMapper.updateDictDataType(oldDictType.getDictType(), dictType.getDictType());
        /*修改字典表类型*/
        return dictTypeMapper.updateDictType(dictType);
    }

    @Override
    public String checkDictTypeUnique(SysDictType dictType) {
        SysDictType sysDictType = dictTypeMapper.checkDictTypeUnique(dictType.getDictType());
        return sysDictType == null ? UserConstants.UNIQUE : UserConstants.NOT_UNIQUE;
    }
}
