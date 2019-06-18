package com.ljqiii.dao;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface WxAccountRepository {


    @Insert("insert into wxAccount(openid,nickName,encodedPassword)value(#{openId},#{nickName},#{encodedPassword})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByNickNameEncodedPassword(WxAccount wxAccount);

    @Select("select count(*) from wxAccount where nickName=#{nickName}")
    int selectNicknamecount(@Param("nickName") String nickName);

    @Select("select * from wxaccount where openid=#{openid} limit 1")
    WxAccount findByOpenid(@Param("openid") String openid);

    @Select("select * from wxaccount where id=#{id} limit 1")
    WxAccount findById(@Param("id") Integer id);


    @Insert("insert into wxaccount (openid,session_key) value(#{openId},#{sessionKey})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertOneByWxAccount(WxAccount wxAccount);


    @Update("update wxaccount set nickname=#{nickName},gender=#{gender},city=#{city},province=#{province},country=#{country},avatarUrl=#{avatarUrl} where id=#{id}")
    int updateByWxAccount(WxAccount wxAccount);


    @Select("select nickname from wxaccount where openId=#{openid}")
    String findNickNameByOpenid(@Param("openid") String openid);

    @Select("select * from wxAccount where nickName=#{nickname}")
    List<WxAccount> selectByNickName(@Param("nickname") String nickname);

}
