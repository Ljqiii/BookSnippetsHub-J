package com.ljqiii.dao;

import com.ljqiii.model.Feed;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;


@Mapper
public interface FeedRepository {

    @Select("select * from feed where id=#{id} limit 1")
    Feed findById(int id);

    @Insert("insert into feed (openid,fromopenid,backgroundimageid,bookid,bookcontent,bookcomment)values (#{openid},#{fromopenid},#{backgroundimageid},#{bookid},#{bookcontent},#{bookcomment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByFeed(Feed feed);


    @Select("select * from feed where openid=#{openid}")
    Feed[] findByOpenid(String openid);

    @Delete("delete from feed where id=#{id}")
    int deleteById(int id);

    @Select("select count(*) from feed where openid=#{openId}")
    int selectFeedCountByWxAccount(WxAccount wxAccount);

    @Select("select count(*) from feed where openid=#{openid}")
    int selectFeedCountByOpenId(String openid);

    Feed[] findFeedRand(@Param("count") int count, @Param("notin") ArrayList<Integer> notin);

    Feed[] findFeedByBookid(@Param("count") int count, @Param("notin") ArrayList<Integer> notin, @Param("bookid") int bookid);

    Feed[] findFeedByOpenid(@Param("count") int count, @Param("notin") ArrayList<Integer> notin, @Param("openid") String openid);


}
