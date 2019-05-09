package com.ljqiii.dao;

import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.*;


@Mapper
public interface WxAccountRepository {

    @Select("select * from wxaccount where openid=#{openid} limit 1")
    WxAccount findByOpenid(String openid);

    @Select("select * from wxaccount where id=#{id} limit 1")
    WxAccount findById(Integer id);


    @Insert("insert into wxaccount (openid,session_key) value(#{openId},#{sessionKey})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertOneByWxAccount(WxAccount wxAccount);


}
