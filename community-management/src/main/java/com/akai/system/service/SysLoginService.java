package com.akai.system.service;

public interface SysLoginService {
    /*返回token，即jwt*/
    String login(String userName, String password, String code, String uuid);
}
