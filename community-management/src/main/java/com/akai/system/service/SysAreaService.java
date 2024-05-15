package com.akai.system.service;

import com.akai.system.domain.dto.SysAreaDto;

import java.util.List;

public interface SysAreaService {
    // 获取区域数据的完整树
    List<SysAreaDto> findAreaAsTree();
}
