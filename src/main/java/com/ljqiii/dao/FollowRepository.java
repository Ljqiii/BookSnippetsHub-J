package com.ljqiii.dao;

import com.ljqiii.model.Follow;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FollowRepository {


    @Insert("insert into follow(openid, followopenid) values (#{openid},#{followopenid})")
    int insertByFollow(Follow follow);

    @Select("select count(*) from follow where followopenid=#{followopenid}")
    int selectFollowersCountByOpenid(String followopenid);

    @Select("select count(*) from follow where followopenid=#{openId}")
    int selectFollowersCountByWxaccount(WxAccount wxAccount);

    @Select("select count(*) from follow where openid=#{openId}")
    int selectFollowsCountByWxaccount(WxAccount wxAccount);

    @Select("select count(*) from follow where openid=#{openId}")
    int selectFollowsCountByOpenId(String openid);

    @Delete("delete from follow where openid=#{openid} and followopenid=#{followopenid}")
    int removeFollowerByFollow(Follow follow);


}
