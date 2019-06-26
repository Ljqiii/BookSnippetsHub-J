package com.ljqiii.config;

import com.ljqiii.config.security.*;
import com.ljqiii.service.WxUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    WxUserDetailService wxUserDetailService;

    @Autowired
    JwtLoginAuthorizationManager jwtAuthorizationManager;


    JwtLoginAuthorizationFilter jwtAuthorizationFilter(JwtLoginAuthorizationManager jwtAuthorizationManager) {
        JwtLoginAuthorizationFilter filter = new JwtLoginAuthorizationFilter("/auth/wxlogin");
        filter.setAuthenticationManager(jwtAuthorizationManager);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().antMatchers("/auth/wxlogin").permitAll();

        //禁用csrf
        http.csrf().disable();


        //监控
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/actuator/**").hasIpAddress("127.0.0.1");
        http.authorizeRequests().antMatchers("/druid/**").hasIpAddress("127.0.0.1");

        //ico
        http.authorizeRequests().antMatchers("/favicon.ico").permitAll();

        //登录
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/signup").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/restlogin").permitAll();

        //错误
        http.authorizeRequests().antMatchers("/error").permitAll();

        //静态资源
        http.authorizeRequests().antMatchers("/sysimg/**").permitAll();


        //测试用
        http.authorizeRequests().antMatchers("/test**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/test**").permitAll();
        http.authorizeRequests().antMatchers("/hello").permitAll();



        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthorizationFilter(jwtAuthorizationManager), UsernamePasswordAuthenticationFilter.class);
     }


}
