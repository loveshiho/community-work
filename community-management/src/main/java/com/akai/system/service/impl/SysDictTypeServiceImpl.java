package com.akai.system.service.impl;

import com.akai.common.constant.UserConstants;
import com.akai.common.utils.Constants;
import com.akai.common.utils.RedisCache;
import com.akai.system.domain.SysDictData;
import com.akai.system.domain.SysDictType;
import com.akai.system.mapper.SysDictDataMapper;
import com.akai.system.mapper.SysDictTypeMapper;
import com.akai.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;
    @Autowired
    private SysDictDataMapper dictDataMapper;
    @Autowired
    private RedisCache redisCache;

    /*项目启动时初始化字典到缓存*/
    @PostConstruct
    public void init() {
        List<SysDictType> sysDictTypes = dictTypeMapper.selectDictTypeAll();
        for (SysDictType sysDictType : sysDictTypes) {
            List<SysDictData> list = dictDataMapper.selectDictDataByType(sysDictType.getDictType());
            redisCache.setCacheList(getRedisKey(sysDictType.getDictType()), list);
        }
    }

    public String getRedisKey(String type) {
        return Constants.SYS_DICT_KEY + type;
    }

    public void clear() {
        Collection<String> keys = redisCache.keys(Constants.SYS_DICT_KEY + "*");
        redisCache.deleteObject(keys);
    }

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
        List<SysDictData> cacheList = redisCache.getCacheList(getRedisKey(dictType), SysDictData.class);
        if (!Objects.isNull(cacheList)) return cacheList;
        cacheList = dictDataMapper.selectDictDataByType(dictType);
        if (!Objects.isNull(cacheList)) redisCache.setCacheList(getRedisKey(dictType), cacheList);
        return cacheList;
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
        int rows = dictTypeMapper.deleteDictTypeByIds(dictIds);
        if (rows > 0) clear();
        return rows;
    }

    @Override
    public int insertDictType(SysDictType dictType) {
        int rows = dictTypeMapper.insertDictType(dictType);
        if (rows > 0) clear();
        return rows;
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
        int rows = dictTypeMapper.updateDictType(dictType);
        if (rows > 0) clear();
        return rows;
    }

    @Override
    public String checkDictTypeUnique(SysDictType dictType) {
        SysDictType sysDictType = dictTypeMapper.checkDictTypeUnique(dictType.getDictType());
        return sysDictType == null ? UserConstants.UNIQUE : UserConstants.NOT_UNIQUE;
    }

    @Override
    public void clearCache() {
        clear();
    }
}
