package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.dao.FeedRepository;
import com.ljqiii.dao.FollowRepository;
import com.ljqiii.model.WxAccount;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Me {

    @Autowired
    FollowRepository followRepository;

    @Autowired
    FeedRepository feedRepository;


    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @GetMapping("/me")
    public JSONObject me(WxAuthenticationToken wxAuthenticationToken){

        JSONObject responejson=new JSONObject();
        WxAccount wxAccount= (WxAccount) wxAuthenticationToken.getPrincipal();

        int followcount=followRepository.selectFollowsCountByWxaccount(wxAccount);
        int followerscount=followRepository.selectFollowersCountByWxaccount(wxAccount);
        int feed=feedRepository.selectFeedCountByWxAccount(wxAccount);

        responejson.put("errcode",0);
        responejson.put("followcount",followcount);
        responejson.put("followerscount",followerscount);
        responejson.put("feed",feed);

        return responejson;
        
    }
}
