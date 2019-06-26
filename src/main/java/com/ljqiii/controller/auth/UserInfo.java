package com.ljqiii.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.UserInfoService;
import com.ljqiii.service.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.IOException;

@RestController
public class UserInfo {


    @Autowired
    WxAccountService wxAccountService;

    @Autowired
    UserInfoService userInfoService;


    @GetMapping("/getuserinfo")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject getUserInfo(WxAuthenticationToken wxAuthenticationToken) {
        JSONObject responsejson = new JSONObject();

        WxAccount wxAccount = wxAccountService.getWxAccount((Integer) wxAuthenticationToken.getCredentials());
        responsejson.put("nickname", wxAccount.getNickName());
        String avatarUrl = wxAccount.getAvatarUrl();
        if (avatarUrl == null || avatarUrl.equals("")) {
            responsejson.put("avatarUrl", "/sysimg/defaultavatar.png");
        } else {
            responsejson.put("avatarUrl", wxAccount.getAvatarUrl());
        }
        return responsejson;
    }


    @PostMapping("/setavatar")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject setAvatar(ServletRequest request, WxAuthenticationToken wxAuthenticationToken, @RequestParam("file") MultipartFile[] multipartFile,ServletRequest servletRequest)  {

        JSONObject responsejson = new JSONObject();
        MultipartFile avatar = multipartFile[0];

        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();


        try {
            String avatarname= userInfoService.changAvatar(wxAccount,avatar);

            String contentpath=servletRequest.getServletContext().getContextPath();
            if(!contentpath.equals("")){
                contentpath=contentpath+"/";
            }
            responsejson.put("errcode",0);
            responsejson.put("url",servletRequest.getScheme()+"://"+servletRequest.getServerName()+":"+servletRequest.getServerPort()+"/"+contentpath+"avatar/"+avatarname);

        } catch (IOException e) {
            e.printStackTrace();
            responsejson.put("errcode",1);
        }



        return responsejson;
    }


}
