package com.ljqiii.dao;


import com.ljqiii.model.FeedComment;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FeedCommentRepository {


    @Select("select count(*) from feedcomment where feedid=#{feedid} and openid=#{openid}")
    int usercounts(@Param("feedid") int feedid,@Param("openid") String openid);

    @Select("select count(*) from feedcomment where feedid=#{feedid} ")
    int count(@Param("feedid") int feedid);

    @Insert("insert into feedcomment(feedid, openid, comment) values (#{feedid},#{openid},#{comment})")
    int insert(@Param("feedid") int feedid,@Param("openid") String openid,@Param("comment") String comment);

    @Insert("insert into feedcomment(feedid, openid, comment) values (#{feedid},#{openid},#{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByFeedComment(FeedComment feedComment);

    @Select("select * from feedcomment where feedid=#{feedid}")
    FeedComment[] selectAllByFeedId(int feedid);

}
