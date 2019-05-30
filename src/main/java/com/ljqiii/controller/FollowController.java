package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping("/follow")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject follow(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject jsonObject) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        int userid = jsonObject.getInteger("userid");

        JSONObject responejson = new JSONObject();
        boolean result = followService.follow(wxAccount, userid);
        responejson.put("isok", result);
        return responejson;
    }

    @PostMapping("/disfollow")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject disfollow(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject jsonObject) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        int userid = jsonObject.getInteger("userid");

        JSONObject responejson = new JSONObject();
        boolean result = followService.disfollow(wxAccount, userid);
        responejson.put("isok", result);
        return responejson;
    }

    @GetMapping("/getfollow")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public ArrayList<JSONObject> getfollow(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        return followService.getfollowlist(wxAccount);
    }

    @GetMapping("/getfollower")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public ArrayList<JSONObject> getfollower(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        return followService.getfollowerlist(wxAccount);
    }


}
