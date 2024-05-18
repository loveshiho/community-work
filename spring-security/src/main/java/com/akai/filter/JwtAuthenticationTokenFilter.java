package com.akai.filter;

import com.akai.pojo.LoginUser;
import com.akai.utils.JwtUtils;
import com.akai.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*自定义认证过滤器,用来校验用户请求中携带的Token*/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    /*封装过滤器的执行逻辑*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*1 获取token*/
        String token = request.getHeader("token");
        /*2 判断token是否为空,为空直接放行*/
        if (token == null || token == "") {
            filterChain.doFilter(request, response);
            /*return的作用是返回响应的时候,避免走下面的逻辑*/
            return;
        }
        /*3 解析jwt*/
        Claims claims = null;
        try {
            claims = JwtUtils.parseJwt(token);
        } catch (Exception e) {
            throw new RuntimeException("非法token");
        }
        /*4 获取userId*/
        String userId = claims.getSubject();
        /*5 从redis中获取user信息*/
        String redisKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey, LoginUser.class);
        if (loginUser == null) {
            throw new RuntimeException("用户未登录");
        } else {
            /*6 将用户新保存到 SecurityContextHolder,以便后续的访问控制和授权操作使用*/
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        /*7 放行*/
        filterChain.doFilter(request, response);
    }
}
