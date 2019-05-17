package com.ljqiii.service;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UploadUserInfo {

    @Autowired
    WxAccountRepository wxAccountRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean upload(int id,JSONObject userinfo){
        WxAccount wxAccount=wxAccountRepository.findById(id);
        userinfo=userinfo.getJSONObject("userinfo");

        wxAccount.setCountry(userinfo.getString("country"));
        wxAccount.setGender(userinfo.getString("gender"));
        wxAccount.setProvince(userinfo.getString("province"));
        wxAccount.setCity(userinfo.getString("city"));
        wxAccount.setAvatarUrl(userinfo.getString("avatarUrl"));
        wxAccount.setAvatarUrl(userinfo.getString("avatarUrl"));
        wxAccount.setNickName(userinfo.getString("nickName"));
//       wxAccount.set(userinfo.getString("language"));
        wxAccountRepository.updateByWxAccount(wxAccount);
        return true;

    }

}
