package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Feed {


    @PostMapping("/addfeed")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject addFeed(WxAuthenticationToken wxAuthenticationToken,@RequestBody JSONObject feed) {

        int wxuserid= (int) wxAuthenticationToken.getCredentials();

        JSONObject respone = new JSONObject();
        respone.put("1", "11");
        return respone;
    }
}
