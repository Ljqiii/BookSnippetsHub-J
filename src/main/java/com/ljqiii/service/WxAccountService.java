package com.ljqiii.service;


import com.ljqiii.model.WxAccount;
import org.springframework.stereotype.Service;

@Service
public interface WxAccountService {

    public WxAccount addAccount(String nickName, String password);

    public String code2Session(String js_code);

    public boolean isNicknameExist(String nickName);
}
