package com.ljqiii.service;


import org.springframework.stereotype.Service;

@Service
public interface WxAccountService {
    public String code2Session(String js_code);
}
