package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.dao.FeedLikeRepository;
import com.ljqiii.model.Feed;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.FeedService;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FeedController {

    @Autowired
    FeedService feedService;


    @Autowired
    FeedLikeRepository feedLikeRepository;


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


    @PostMapping("/getbookfeed")
    public ArrayList<JSONObject> getFeedByBook(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
        ArrayList<Integer> notin = requestjson.getObject("allfeedid", ArrayList.class);
        int bookid = requestjson.getInteger("bookid");
        return feedService.getFeedByBookid(10, notin, bookid);
    }

    @PostMapping("/getfeed")
    public ArrayList<JSONObject> getFeedById(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
        int feedid = requestjson.getInteger("feedid");
        return feedService.getFeedById(feedid);
    }


    @PostMapping("/getrecommendfeed")
    public ArrayList<JSONObject> getrecommendfeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
//        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        ArrayList<Integer> notin = requestjson.getObject("allrecommendfeedsid", ArrayList.class);

        return feedService.getFeedRand(1000, notin);
    }


    @PostMapping("/getallmylikefeed")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public ArrayList<JSONObject> getallmylikefeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
//        ArrayList<Integer> notin = requestjson.getObject("allmylikefeedid", ArrayList.class);
        ArrayList<Integer> notin=new ArrayList<>();

        return feedService.getFeedByOpenid(10, notin,wxAccount.getOpenId());
    }


    @RequestMapping("/likefeed")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject likefeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject jsonObject) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        int feedid = jsonObject.getInteger("feedid");

        feedLikeRepository.insert(feedid, wxAccount.getOpenId());
        JSONObject responejson = new JSONObject();
        responejson.put("isok", true);
        return responejson;
    }


    @RequestMapping("/dislikefeed")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject dislikefeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject jsonObject) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        int feedid = jsonObject.getInteger("feedid");

        feedLikeRepository.delete(feedid, wxAccount.getOpenId());
        JSONObject responejson = new JSONObject();
        responejson.put("isok", true);
        return responejson;
    }



    @PostMapping("/forward")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject forwardfeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject jsonObject){
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        int feedid = jsonObject.getInteger("feedid");

        boolean result=feedService.forwardFeed(wxAccount.getOpenId(),feedid);

        JSONObject responejson=new JSONObject();
        responejson.put("isok",result);
        return responejson;

    }

    @PostMapping("/disforward")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject disForwardfeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject jsonObject){
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        int feedid = jsonObject.getInteger("feedid");

        boolean result=feedService.disForwardFeed(wxAccount.getOpenId(),feedid);

        JSONObject responejson=new JSONObject();
        responejson.put("isok",result);
        return responejson;

    }



}
