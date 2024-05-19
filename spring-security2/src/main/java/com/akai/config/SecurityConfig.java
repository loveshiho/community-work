package com.akai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()    // 开始配置授权，即允许哪些请求访问系统
                .mvcMatchers("/login.html").permitAll()   // 指定哪些请求路径允许访问
                .mvcMatchers("/index").permitAll()      // 指定哪些请求路径允许访问
                .anyRequest().authenticated()  // 除上述以外,指定其他所有请求都需要经过身份验证
                .and()
                .formLogin()    //配置表单登录
                .loginPage("/login.html")      // 登录页面
                .loginProcessingUrl("/login")  // 提交路径
                .usernameParameter("username") // 表单中用户名
                .passwordParameter("password") // 表单中密码
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                //.successForwardUrl("/index")  // 指定登录成功后要跳转的路径为 /index
                //.defaultSuccessUrl("/index")   // redirect 重定向  注意:如果之前请求路径,会优先跳转之前请求路径
                //.failureUrl("/login.html") // 指定登录失败后要跳转的路径为 /login.html
                .and()
                .logout()	// 开启注销配置
                // .logoutUrl("/logout")	// 退出登录地址
                .invalidateHttpSession(true)		// 退出时 session是否失效，默认为 true
                .clearAuthentication(true)		// 退出时是否清空认证信息，默认为 true
                .logoutSuccessHandler(logoutSuccessHandler)
                // .logoutSuccessUrl("/login.html")		// 退出登录时，跳转的地址
                .and()
                .csrf().disable();  // 这里先关闭 CSRF
    }
}
