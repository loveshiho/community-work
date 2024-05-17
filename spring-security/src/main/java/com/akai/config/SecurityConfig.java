package com.akai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*使用随机盐值对密码进行加密*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*该方法用于配置 HTTP请求的安全处理
    * 类似于对某些接口进行放行、关闭csrf
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不会去创建会话，每个请求都被视为独立的请求，STATELESS表示无状态
                .and()
                // 定义请求的授权规则
                .authorizeRequests()
                // 对登录接口放行，允许匿名访问
                .antMatchers("/user/login").anonymous()
                // 除上面外所有的请求，都需要鉴权认证
                .anyRequest().authenticated()
        ;
    }
    /*注册AuthenticationManager，供外部类使用*/
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
