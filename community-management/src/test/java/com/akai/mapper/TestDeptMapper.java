package com.akai.mapper;

import com.akai.system.domain.SysDept;
import com.akai.system.mapper.SysDeptMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestDeptMapper {
    @Autowired
    private SysDeptMapper mapper;

    @Test
    void testSelect() {
        SysDept sysDept = mapper.selectDeptById(100l);
        System.out.println(sysDept);
    }

    @Test
    void testSelect2() {
        List<SysDept> sysDepts = mapper.selectDeptList(null);
        System.out.println(sysDepts);
    }

    @Test
    void testSelect3() {
        List<SysDept> sysDepts = mapper.selectChildrenDeptById(100l);
        System.out.println(sysDepts);
    }
}
