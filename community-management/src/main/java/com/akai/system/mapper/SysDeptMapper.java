package com.akai.system.mapper;

import com.akai.system.domain.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SysDeptMapper extends BaseMapper<SysDept> {
    List<SysDept> selectList();
}
