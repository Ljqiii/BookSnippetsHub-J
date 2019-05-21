package com.ljqiii.dao;


import com.ljqiii.model.FeedComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FeedCommentRepository {


    @Select("select count(*) from feedcomment where feedid=#{arg0} and openid=#{arg1}")
    int usercounts(int feedid, String openid);

    @Select("select count(*) from feedcomment where feedid=#{arg0} ")
    int count(int feedid);

    @Insert("insert into feedcomment(feedid, openid, comment) values (#{arg0},#{arg1},#{arg2})")
    int insert(int feedid, String openid, String comment);

    @Insert("insert into feedcomment(feedid, openid, comment) values (#{feedid},#{openid},#{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByFeedComment(FeedComment feedComment);

    @Select("select * from feedcomment where feedid=#{feedid}")
    FeedComment[] selectAllByFeedId(int feedid);

}
