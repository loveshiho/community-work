package com.akai.system.service.impl;

import com.akai.system.domain.SysArea;
import com.akai.system.domain.dto.SysAreaDto;
import com.akai.system.mapper.SysAreaMapper;
import com.akai.system.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysAreaServiceImpl implements SysAreaService {
    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Override
    public List<SysAreaDto> findAreaAsTree() {
        // 获取区域表数据
        List<SysArea> sysAreas = sysAreaMapper.findAll();
        List<SysAreaDto> sysAreaDtos = new ArrayList<>();
        Collections.sort(sysAreas, new Comparator<SysArea>() {
            @Override
            public int compare(SysArea o1, SysArea o2) {
                return o1.getParentId() - o2.getParentId();
            }
        });
        // 转换为完整树
        for (int i = 0; i < sysAreas.size(); i++) {
            SysArea sysArea = sysAreas.get(i);
            if (sysArea.getParentId() == -1) {
                continue;
            }
            if (sysArea.getParentId() > 0) {
                break;
            }
            SysAreaDto sysAreaDto = new SysAreaDto();
            sysAreaDto.setCode(sysArea.getCode());
            sysAreaDto.setName(sysArea.getName());
            sysAreaDto.setChildren(getChildrenAsTree(sysArea.getCode(), i, sysAreas));
            sysAreaDtos.add(sysAreaDto);
        }
        return sysAreaDtos;
    }

    // 递归含义：给你 code、arr、当前所在 index，给我返回整颗树
    /*牛逼，递归完全正确*/
    public List<SysAreaDto> getChildrenAsTree(int code, int index, List<SysArea> sysAreas) {
        List<SysAreaDto> sysAreaDtos = new ArrayList<>();

        for (int i = index + 1; i < sysAreas.size(); i++) {
            SysArea sysArea = sysAreas.get(i);
            if (sysArea.getParentId() == code) {
                SysAreaDto sysAreaDto = new SysAreaDto();
                sysAreaDto.setCode(sysArea.getCode());
                sysAreaDto.setName(sysArea.getName());
                sysAreaDto.setChildren(getChildrenAsTree(sysArea.getCode(), i, sysAreas));
                sysAreaDtos.add(sysAreaDto);
            }
        }
        return sysAreaDtos;
    }
}
