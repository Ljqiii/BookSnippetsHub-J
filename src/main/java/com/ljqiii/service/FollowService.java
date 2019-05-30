package com.ljqiii.service;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.FollowRepository;
import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FollowService {


    @Autowired
    FollowRepository followRepository;
    @Autowired
    WxAccountRepository wxAccountRepository;


    public boolean follow(WxAccount wxAccount, int userid) {
        if (wxAccount == null) {
            return false;
        } else {
            WxAccount followwxaccount = wxAccountRepository.findById(userid);
            if (followwxaccount != null) {
                followRepository.insert(wxAccount.getOpenId(), followwxaccount.getOpenId());
                return true;
            }
            return false;
        }
    }

    public boolean disfollow(WxAccount wxAccount, int userid) {
        if (wxAccount == null) {
            return false;
        } else {
            WxAccount followwxaccount = wxAccountRepository.findById(userid);
            if (followwxaccount != null) {
                if (followRepository.delete(wxAccount.getOpenId(), followwxaccount.getOpenId()) > 0) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public ArrayList<JSONObject> getfollowlist(WxAccount wxAccount) {
        String openid = wxAccount.getOpenId();
        String[] followopenids = followRepository.selectAllFollowsByOpenid(openid);

        return openidstoArray(followopenids);
    }


    public ArrayList<JSONObject> getfollowerlist(WxAccount wxAccount) {
        String openid = wxAccount.getOpenId();
        String[] followeropenids = followRepository.selectAllFollowersByOpenid(openid);

        return openidstoArray(followeropenids);
    }

    ArrayList<JSONObject> openidstoArray(String[] openids) {
        ArrayList<JSONObject> followlist = new ArrayList<>();
        for (String followweopenid : openids) {
            WxAccount temp = wxAccountRepository.findByOpenid(followweopenid);
            JSONObject user = new JSONObject();
            user.put("nickname", temp.getNickName());
            user.put("AvatarUrl", temp.getAvatarUrl());
            followlist.add(user);
        }
        return followlist;
    }

}
