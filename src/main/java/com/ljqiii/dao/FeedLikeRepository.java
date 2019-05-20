package com.ljqiii.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FeedLikeRepository {


    @Select("select count(*) from feedlike where feedid=#{feedid}")
    int selectFeedLikeCount(@Param("feedid") int feedid);

    @Insert("insert into feedlike(feedid, openid) values (#{arg0},#{arg1})")
    int insert(int feedid,String openid);


    @Select("select  count(*) from  feedlike where feedid=#{arg0} and openid=#{arg1}")
    int selectCountByFeedIdOpenid(int feedid, String openid);

}
