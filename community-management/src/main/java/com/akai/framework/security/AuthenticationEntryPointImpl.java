package com.akai.framework.security;

import com.akai.common.constant.HttpStatus;
import com.akai.common.core.domain.BaseResponse;
import com.akai.common.utils.ServletUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int code = HttpStatus.UNAUTHORIZED;
        ServletUtils.renderString(response, JSON.toJSONString(BaseResponse.fail(String.valueOf(code), "认证失败")));
    }
}
