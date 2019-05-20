package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;


    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @GetMapping("/getnotification")
    public ArrayList<JSONObject> getmsg(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount= (WxAccount) wxAuthenticationToken.getPrincipal();

//        JSONObject responejson = new JSONObject();
        ArrayList<JSONObject> notifications = notificationService.getAllNotificationByToOpenid(wxAccount.getOpenId());

        return notifications;


    }


}
