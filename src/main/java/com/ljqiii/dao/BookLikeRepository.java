package com.ljqiii.dao;


import com.ljqiii.model.Book;
import com.ljqiii.model.BookLike;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BookLikeRepository {

    @Select("select * from booklike where id=#{id} limit 1")
    BookLike findById(int id);

    @Insert("insert into booklike (openid, bookid)values (#{openid},#{bookid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByBookLike(BookLike bookLike);


    @Select("select * from booklike")
    BookLike[] findAllBook();

    @Select("select * from booklike where openid=#{openid}")
    BookLike[] findAllByOpenid(String openid);


    @Select("insert into booklike (openid, bookid)values (#{openid},#{bookid})")
    void insert(@Param("openid") String openid,@Param("bookid") int bookid);

    @Delete("delete from booklike where openid=#{openid} and bookid=#{bookid}")
    int delete(@Param("openid") String openid, @Param("bookid") int bookid);

    @Delete("delete from booklike where openid=#{openid} and bookid=#{bookid}")
    int deleteByBookidLike(BookLike bookLike);

    @Select("select count(*) from booklike where openid=#{openid} and bookid=#{bookid}")
    int count(@Param("openid") String openid,@Param("bookid") int bookid);

}
