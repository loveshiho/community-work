package com.akai.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:user:list')")
    public String hello() {
        return "hello user~";
    }

    @RequestMapping("/ok")
    @PreAuthorize("hasAuthority('system:role:list')")
    public String ok() {
        return "hello role~";
    }

    @RequestMapping("/hi")
    @PreAuthorize("hasAuthority('system:menu:list')")
    public String hi() {
        return "hello menu~";
    }

    @RequestMapping("haha")
    @PreAuthorize("hasAnyAuthority('system:user:list', 'system:role:list', 'system:menu:list')")
    public String haha() {
        return "hello haha~";
    }

    @RequestMapping("/yes")
    @PreAuthorize("@ex.hasAuthority('system:menu:list')")
    public String yes() {
        return "hello yes~";
    }

    @RequestMapping("/p")
    public String p() {
        return "hello p~";
    }

    @RequestMapping("level")
    @PreAuthorize("hasRole('admin')")
    public String level() {
        return "hello level~";
    }
}
