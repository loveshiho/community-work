package com.akai.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
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
}
