package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.FeedCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;

@RestController
public class FeedCommentController {


    @Autowired
    FeedCommentService feedCommentService;


    @PostMapping("/getallcomment")

    public ArrayList<JSONObject> getallcomment(@RequestBody JSONObject requestjson) {

        return feedCommentService.getallByFeedId(requestjson.getInteger("feedid"));
    }

    @PostMapping("/addcomment")
    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    public JSONObject addcomment(WxAuthenticationToken wxAuthenticationToken, @RequestBody JSONObject requestjson) {
        JSONObject responejson = new JSONObject();

        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();
        String openid = wxAccount.getOpenId();

        int feedid = requestjson.getInteger("feedid");
        String commentvalue = requestjson.getString("commentvalue");



        responejson.put("isok", feedCommentService.addFeedComment(openid, feedid, commentvalue));

        JSONObject commentcontent=new JSONObject();
        commentcontent.put("AvatarUrl",wxAccount.getAvatarUrl());
        commentcontent.put("comment",commentvalue);
        commentcontent.put("nickname",wxAccount.getNickName());

        responejson.put("comment",commentcontent);

        return responejson;
    }

}
