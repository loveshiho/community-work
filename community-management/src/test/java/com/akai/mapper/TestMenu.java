package com.akai.mapper;

import com.akai.system.domain.SysMenu;
import com.akai.system.mapper.SysMenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMenu {
    @Autowired
    private SysMenuMapper menuMapper;
    @Test
    void testMenu() {
        List<SysMenu> sysMenus = menuMapper.selectMenuTreeAll();
        sysMenus.forEach(System.out::println);
    }
    @Test
    void testMenu2() {
        List<SysMenu> sysMenus = menuMapper.selectMenuTreeByUserId(2l);
        sysMenus.forEach(System.out::println);
    }
}
