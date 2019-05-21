package com.ljqiii.service;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.FeedCommentRepository;
import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.FeedComment;
import com.ljqiii.model.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FeedCommentService {


    @Autowired
    FeedCommentRepository feedCommentRepository;

    @Autowired
    WxAccountRepository wxAccountRepository;


    public ArrayList<JSONObject> feedCommentResopne(FeedComment[] feedComments) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();


        for (FeedComment feedComment : feedComments) {
            JSONObject temp = new JSONObject();

            temp.put("comment", feedComment.getComment());

            int id = feedComment.getId();

            String openid = feedComment.getOpenid();
            String comment = feedComment.getComment();

            WxAccount wxAccount = wxAccountRepository.findByOpenid(openid);
            if (wxAccount != null) {
                temp.put("nickname", wxAccount.getNickName());
                temp.put("AvatarUrl", wxAccount.getAvatarUrl());
            } else {
                temp.put("nickname", "");
                temp.put("AvatarUrl", "");
            }

            jsonObjects.add(temp);
        }
        return jsonObjects;
    }


    public ArrayList<JSONObject> getallByFeedId(int feedid) {
        FeedComment[] feedComments = feedCommentRepository.selectAllByFeedId(feedid);
        return feedCommentResopne(feedComments);
    }

    public boolean addFeedComment(String openid, int feedid, String feedcomment) {

        feedCommentRepository.insert(feedid, openid, feedcomment);
        return true;
    }

}
