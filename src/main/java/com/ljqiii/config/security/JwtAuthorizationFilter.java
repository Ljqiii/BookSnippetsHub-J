package com.ljqiii.config.security;

import com.alibaba.fastjson.JSONObject;

import com.ljqiii.model.WxAccount;
import com.ljqiii.utils.HttpServletRequestUtil;
import com.ljqiii.utils.HttpServletResponseUtil;
import com.ljqiii.utils.JsonUtil;
import com.ljqiii.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;



public class JwtAuthorizationFilter extends AbstractAuthenticationProcessingFilter {



    public JwtAuthorizationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {


        System.out.println(failed.getMessage());

        System.out.println("in unsuccessfulAuthentication");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errcode", "1");
        jsonObject.put("errmsg", failed.getMessage());

        HttpServletResponseUtil.returnJson(response, jsonObject);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("in successfulAuthentication");

        String openid = (String) authResult.getCredentials();

        String token = JwtTokenUtil.generateToken(openid);

        JSONObject jsonrestlt = new JSONObject();
        jsonrestlt.put("errcode", "0");
        jsonrestlt.put("token", token);


        HttpServletResponseUtil.returnJson(response, jsonrestlt);

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("In JwtAuthorizationFilter");
        String requestbody = HttpServletRequestUtil.RequestBody2String(request);
        System.out.println(requestbody);


        String js_code;

        JSONObject jsonObject = JSONObject.parseObject(requestbody);

        if (jsonObject == null) {
            throw new BadJsCodeException("bad js_code");
        } else {
            js_code = jsonObject.getString("js_code");
            if (js_code == null) {

                throw new BadJsCodeException("bad js_code");
            }
        }
        System.out.println(js_code);

        return this.getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken("", js_code));

    }


}
