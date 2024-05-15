package com.akai.system.service;

import com.akai.system.domain.dto.SysAreaDto;

import java.util.List;

public interface SysAreaService {
    List<SysAreaDto> findAreaAsTree();
}
