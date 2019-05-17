package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.Feed;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @Autowired
    FeedService feedService;


    @PostMapping("/addfeed")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject addFeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {

        int wxuserid = (int) wxAuthenticationToken.getCredentials();
        String openid = ((WxAccount) wxAuthenticationToken.getPrincipal()).getOpenId();

        JSONObject respone = new JSONObject();

        if (feedService.addSelfFeed(requestjson, openid) == true) {
            respone.put("errcode", "0");
        } else {
            respone.put("errcode", "1");
        }
        return respone;
    }
}
