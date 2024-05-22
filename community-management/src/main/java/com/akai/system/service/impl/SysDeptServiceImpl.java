package com.akai.system.service.impl;

import com.akai.common.constant.UserConstants;
import com.akai.common.core.exception.CustomException;
import com.akai.system.domain.SysDept;
import com.akai.system.domain.TreeSelect;
import com.akai.system.mapper.SysDeptMapper;
import com.akai.system.service.SysDeptService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        return sysDeptMapper.selectDeptList(dept);
    }

    @Override
    public SysDept selectDeptById(Long deptId) {
        return sysDeptMapper.selectDeptById(deptId);
    }

    @Override
    public int insertDept(SysDept dept) {
        /*获取当前节点的父节点*/
        SysDept father = sysDeptMapper.selectDeptById(dept.getParentId());
        if (!father.getStatus().equals(UserConstants.DEPT_NORMAL)) throw new CustomException(500, "部门停用");
        /*设置父节点*/
        dept.setAncestors(father.getAncestors() + "," + father.getDeptId());
        return sysDeptMapper.insertDept(dept);
    }

    /*修改部门信息*/
    @Override
    public int updateDept(SysDept dept) {
        /*涉及到父节点变更*/
        SysDept father = sysDeptMapper.selectDeptById(dept.getParentId());
        if (!Objects.isNull(father)) {
            String fatherList = father.getAncestors() + "," + father.getDeptId();
            dept.setAncestors(fatherList);
            /*部门有子节点，需要更改子节点*/
            updateDeptChilren(dept.getDeptId(), dept);
        }
        return sysDeptMapper.updateDept(dept);
    }

    /*修改子元素父节点*/
    private void updateDeptChilren(Long deptId, SysDept father) {
        List<SysDept> sysDepts = sysDeptMapper.selectChildrenDeptById(deptId);
        if (sysDepts.size() > 0) {
            for (SysDept sysDept : sysDepts) {
                sysDept.setAncestors(father.getAncestors() + "," + father.getDeptId());
            }
        }
        sysDeptMapper.updateDeptChildren(sysDepts);
    }

    @Override
    public int deleteDeptById(Long deptId) {
        return sysDeptMapper.deleteDeptById(deptId);
    }

    @Override
    public String checkDeptNameUnique(SysDept dept) {
        SysDept sysDept = sysDeptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (sysDept != null) return UserConstants.NOT_UNIQUE;
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean hasChildByDeptId(Long deptId) {
        return !(sysDeptMapper.hasChildByDeptId(deptId) == 0);
    }

    @Override
    public boolean checkDeptExistUser(Long deptId) {
        return !(sysDeptMapper.checkDeptExistUser(deptId) == 0);
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> sysDepts) {
        if (sysDepts == null) return null;
        return buildTree(sysDepts);
    }

    private List<TreeSelect> buildTree(List<SysDept> sysDepts) {
        buildChildren(sysDepts);
        List<TreeSelect> list = new ArrayList<>();
        for (SysDept sysDept : sysDepts) {
            list.add(new TreeSelect(sysDept));
        }
        return list;
    }

    private void buildChildren(List<SysDept> sysDepts) {
        /*sysDepts已经有序*/
        for (int i = 0; i < sysDepts.size(); i++) {
            SysDept sysDept = sysDepts.get(i);
            if (sysDept.getParentId() > 0) return;
            sysDept.setChildren(getChildrenAsTree(sysDept.getDeptId(), i, sysDepts));
        }
    }

    private List<SysDept> getChildrenAsTree(Long deptId, int index, List<SysDept> sysDepts) {
        List<SysDept> children = new ArrayList<>();
        for (int i = index + 1; i < sysDepts.size(); i++) {
            SysDept sysDept = sysDepts.get(i);
            if (sysDept.getParentId() == deptId) {
                sysDept.setChildren(getChildrenAsTree(sysDept.getDeptId(), i, sysDepts));
                children.add(sysDept);
            }
        }
        return children;
    }
}
