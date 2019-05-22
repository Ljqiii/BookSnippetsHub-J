package com.ljqiii.controller.auth;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.UploadUserInfo;
import com.ljqiii.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@RestController
public class Login {

    @Autowired
    UploadUserInfo uploadUserInfo;

    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/auth/wxlogin")
    public JSONObject wxLogin(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();

        String token = (String) wxAuthenticationToken.getCredentials();

        JSONObject jsonrestlt = new JSONObject();
        jsonrestlt.put("errcode", "0");
        jsonrestlt.put("token", token);
        jsonrestlt.put("userid", wxAccount.getId());


        if (wxAccount.getNickName() == null) {
            jsonrestlt.put("hasuserinfo", false);
        } else {
            jsonrestlt.put("hasuserinfo", true);
        }
        return jsonrestlt;
    }


    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/auth/uploadWxUserinfo")
    public String uploadWxUserinfo(@RequestBody JSONObject userinfo, WxAuthenticationToken wxAuthenticationToken) throws IOException {

        uploadUserInfo.upload((Integer) wxAuthenticationToken.getCredentials(),userinfo);

        return "ok";
    }



}
