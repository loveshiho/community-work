package com.akai.system.service.impl;

import com.akai.system.domain.SysArea;
import com.akai.system.domain.dto.SysAreaDto;
import com.akai.system.mapper.SysAreaMapper;
import com.akai.system.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SysAreaServiceImpl implements SysAreaService {
    @Autowired
    private SysAreaMapper sysAreaMapper;

    public SysAreaServiceImpl() {
    }

    public List<SysAreaDto> findAreaAsTree() {
        List<SysArea> sysAreas = this.sysAreaMapper.findAll();
        List<SysAreaDto> sysAreaDtos = new ArrayList();
        Collections.sort(sysAreas, new Comparator<SysArea>() {
            public int compare(SysArea o1, SysArea o2) {
                return o1.getParentId() - o2.getParentId();
            }
        });

        for (int i = 0; i < sysAreas.size(); ++i) {
            SysArea sysArea = (SysArea) sysAreas.get(i);
            if (sysArea.getParentId() != -1) {
                if (sysArea.getParentId() > 0) {
                    break;
                }

                SysAreaDto sysAreaDto = new SysAreaDto();
                sysAreaDto.setCode(sysArea.getCode());
                sysAreaDto.setName(sysArea.getName());
                sysAreaDto.setChildren(this.getChildrenAsTree(sysArea.getCode(), i, sysAreas));
                sysAreaDtos.add(sysAreaDto);
            }
        }

        return sysAreaDtos;
    }

    public List<SysAreaDto> getChildrenAsTree(int code, int index, List<SysArea> sysAreas) {
        List<SysAreaDto> sysAreaDtos = new ArrayList();
        if (index == sysAreas.size()) {
            return null;
        } else {
            for (int i = index + 1; i < sysAreas.size(); ++i) {
                SysArea sysArea = (SysArea) sysAreas.get(i);
                if (sysArea.getParentId() == code) {
                    SysAreaDto sysAreaDto = new SysAreaDto();
                    sysAreaDto.setCode(sysArea.getCode());
                    sysAreaDto.setName(sysArea.getName());
                    sysAreaDto.setChildren(this.getChildrenAsTree(sysArea.getCode(), i, sysAreas));
                    sysAreaDtos.add(sysAreaDto);
                }
            }

            return sysAreaDtos;
        }
    }
}