package com.ljqiii.dao;

import com.ljqiii.model.Feed;
import com.ljqiii.model.T;
import org.apache.ibatis.annotations.*;


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



}
