package com.akai.system.service;

import com.akai.system.domain.LoginUser;

public interface TokenService {
    public String createToken(LoginUser loginUser);
}
