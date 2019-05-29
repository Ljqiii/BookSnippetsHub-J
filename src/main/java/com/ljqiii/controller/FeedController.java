package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.dao.FeedLikeRepository;
import com.ljqiii.dao.FeedRepository;
import com.ljqiii.model.Feed;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.FeedService;
import com.ljqiii.service.NotificationService;
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
    FeedRepository feedRepository;

    @Autowired
    FeedService feedService;


    @Autowired
    NotificationService notificationService;

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
        WxAccount wxAccount;
        if(wxAuthenticationToken!=null){
            wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        }else {
            wxAccount=null;
        }

        ArrayList<Integer> notin = requestjson.getObject("allfeedid", ArrayList.class);
        int bookid = requestjson.getInteger("bookid");
        return feedService.getFeedByBookid(10, notin, bookid,wxAccount);
    }

    @PostMapping("/getfeed")
    public ArrayList<JSONObject> getFeedById(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
        WxAccount wxAccount;
        if (wxAuthenticationToken!=null){
            wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        }else {
            wxAccount=null;
        }
        int feedid = requestjson.getInteger("feedid");
        return feedService.getFeedById(feedid,wxAccount);
    }


    @PostMapping("/getrecommendfeed")
    public ArrayList<JSONObject> getrecommendfeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
        WxAccount wxAccount;
        if(wxAuthenticationToken!=null){
            wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        }else {
            wxAccount=null;
        }
        ArrayList<Integer> notin = requestjson.getObject("allrecommendfeedsid", ArrayList.class);

        return feedService.getFeedRand(10, notin,wxAccount);
    }


    @PostMapping("/getallmylikefeed")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public ArrayList<JSONObject> getallmylikefeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        String openid=wxAccount.getOpenId();

        int alllikeid[]=feedLikeRepository.findallLike(openid);


        return feedService.getFeedsbyId(alllikeid,wxAccount);
    }


    @RequestMapping("/likefeed")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject likefeed(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject jsonObject) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        int feedid = jsonObject.getInteger("feedid");


        Feed feed=feedRepository.findById(feedid);
        notificationService.insertNotification(wxAccount.getOpenId(),feed.getOpenid(),wxAccount.getNickName()+"喜欢了你的分享");



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

        Feed feed=feedRepository.findById(feedid);
        notificationService.insertNotification(wxAccount.getOpenId(),feed.getOpenid(),wxAccount.getNickName()+"转发了你的分享");



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
