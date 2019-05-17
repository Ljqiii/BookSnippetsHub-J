package com.ljqiii.config;

import com.ljqiii.config.security.JwtAuthorizationFilter;
import com.ljqiii.config.security.JwtLoginAuthorizationFilter;
import com.ljqiii.config.security.JwtLoginAuthorizationManager;
import com.ljqiii.service.WxUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

        http.csrf().disable();

        http.authorizeRequests().antMatchers("/druid/**").permitAll();
        http.authorizeRequests().antMatchers("/test**").permitAll();
        http.authorizeRequests().antMatchers("/favicon.ico").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/test**").permitAll();
        http.authorizeRequests().antMatchers("/hello").permitAll();

//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().anyRequest().permitAll();


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthorizationFilter(jwtAuthorizationManager), UsernamePasswordAuthenticationFilter.class);
    }


}
