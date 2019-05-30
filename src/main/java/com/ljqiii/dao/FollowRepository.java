package com.ljqiii.dao;

import com.ljqiii.model.Follow;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.*;

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
    int selectFollowsCountByOpenId(@Param("openid") String openid);

    @Delete("delete from follow where openid=#{openid} and followopenid=#{followopenid}")
    int removeFollowerByFollow(Follow follow);

    @Select("select  followopenid from follow where openid=#{openid}")
    String[] selectAllFollowsByOpenid(@Param("openid") String openid);

    @Select("select openid from follow where followopenid=#{followopenid}")
    String[] selectAllFollowersByOpenid(@Param("followopenid") String followopenid);

    @Select("select count(*) from follow where openid=#{openid} and followopenid=#{followopenid}")
    int selectisfollow(@Param("openid") String openid, @Param("followopenid") String followopenid);

    @Insert("insert into follow(openid, followopenid) values (#{openid},#{followopenid})")
    int insert(@Param("openid") String openid, @Param("followopenid") String followopenid);

    @Delete("delete from follow where openid=#{openid} and followopenid=#{followopenid}")
    int delete(@Param("openid") String openid, @Param("followopenid") String followopenid);
}
