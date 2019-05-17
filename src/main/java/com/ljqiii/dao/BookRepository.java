package com.ljqiii.dao;

import com.ljqiii.model.Feed;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.*;


@Mapper
public interface FeedRepository {

    @Select("select * from feed where id=#{id} limit 1")
    WxAccount findById(String id);

    @Insert("insert into feed (openid,backgroundimageid,bookname,bookcontent,bookcomment)values (#{openid},#{backgroundimageid},#{bookname},#{bookcontent},#{bookcomment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Feed insertByFeed(Feed feed);

    @Select("select * from feed where openid=#{openid}")
    Feed[] findByOpenid(String openid);



}
