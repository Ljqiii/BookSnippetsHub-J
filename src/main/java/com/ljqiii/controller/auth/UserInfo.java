package com.ljqiii.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfo {


    @Autowired
    WxAccountService wxAccountService;


    @GetMapping("/getuserinfo")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject getUserInfo(WxAuthenticationToken wxAuthenticationToken) {
        JSONObject responsejson = new JSONObject();

        WxAccount wxAccount = wxAccountService.getWxAccount((Integer) wxAuthenticationToken.getCredentials());
        responsejson.put("nickname", wxAccount.getNickName());
        String avatarUrl = wxAccount.getAvatarUrl();
        if (avatarUrl==null||avatarUrl.equals("") ) {
            responsejson.put("avatarUrl", "/sysimg/defaultavatar.png");
        } else {
            responsejson.put("avatarUrl", wxAccount.getAvatarUrl());
        }
        return responsejson;
    }


}
