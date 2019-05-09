package com.ljqiii.config;

import com.ljqiii.config.security.JwtAuthorizationFilter;
import com.ljqiii.config.security.JwtAuthorizationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtAuthorizationManager jwtAuthorizationManager;


    JwtAuthorizationFilter jwtAuthorizationFilter(JwtAuthorizationManager  jwtAuthorizationManager) {
        JwtAuthorizationFilter filter = new JwtAuthorizationFilter("/auth/wxlogin");
        filter.setAuthenticationManager(jwtAuthorizationManager);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/test**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthorizationFilter(jwtAuthorizationManager), UsernamePasswordAuthenticationFilter.class);

//
//        http.authorizeRequests()
//                .antMatchers("/t**", "/auth/**").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//
//        http.formLogin().loginPage("/login").permitAll();
//
//        http.csrf().disable();
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//

//
//        http.headers().frameOptions().sameOrigin().cacheControl();

    }



}
