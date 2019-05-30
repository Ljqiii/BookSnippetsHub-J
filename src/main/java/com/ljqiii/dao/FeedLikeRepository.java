package com.ljqiii.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FeedLikeRepository {


    @Select("select count(*) from feedlike where feedid=#{feedid}")
    int selectFeedLikeCount(@Param("feedid") int feedid);

    @Select("select feedid from feedlike where openid=#{openid} order by feedid desc")
    int[] findallLike(@Param("openid") String openid);

    @Insert("insert into feedlike(feedid, openid) values (#{feedid},#{openid})")
    int insert(@Param("feedid") int feedid, @Param("openid") String openid);

    @Insert(" delete from feedlike where  feedid=#{feedid} and openid=#{openid}")
    int delete(@Param("feedid") int feedid, @Param("openid") String openid);

    @Select("select  count(*) from  feedlike where feedid=#{feedid} and openid=#{openid}")
    int selectCountByFeedIdOpenid(@Param("feedid") int feedid, @Param("openid") String openid);

}
