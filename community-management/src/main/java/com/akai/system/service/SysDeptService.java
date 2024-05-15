package com.akai.system.service;

import com.akai.system.domain.SysDept;
import java.util.List;

public interface SysDeptService {
    /*查询部门管理数据*/
    List<SysDept> selectDeptList();
}
