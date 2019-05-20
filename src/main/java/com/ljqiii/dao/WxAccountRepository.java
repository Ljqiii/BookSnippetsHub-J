package com.ljqiii.dao;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;


@Mapper
public interface WxAccountRepository {

    @Select("select * from wxaccount where openid=#{openid} limit 1")
    WxAccount findByOpenid(String openid);

    @Select("select * from wxaccount where id=#{id} limit 1")
    WxAccount findById(Integer id);


    @Insert("insert into wxaccount (openid,session_key) value(#{openId},#{sessionKey})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertOneByWxAccount(WxAccount wxAccount);


    @Update("update wxaccount set nickname=#{nickName},gender=#{gender},city=#{city},province=#{province},country=#{country},avatarUrl=#{avatarUrl} where id=#{id}")
    int updateByWxAccount(WxAccount wxAccount);

    JSONObject selectAllNotification(WxAccount wxAccount);

    @Select("select nickname from wxaccount where openId=#{openid}")
    String findNickNameByOpenid(String openid);

}
