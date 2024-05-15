package com.akai.system.service.impl;

import com.akai.system.domain.SysDept;
import com.akai.system.mapper.SysDeptMapper;
import com.akai.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> selectDeptList() {
        return sysDeptMapper.selectList();
    }
}
