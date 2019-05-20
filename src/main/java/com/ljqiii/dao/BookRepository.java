package com.ljqiii.dao;

import com.ljqiii.model.Book;
import com.ljqiii.model.Feed;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface BookRepository {

    @Select("select * from book where id=#{id} limit 1")
    Book findById(int id);

    @Insert("insert into book (name,bookcoverimg,description)values (#{name},#{bookcoverimg},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByBook(Book book);

    @Select("select * from book where name=#{name}")
    Book findByName(String name);

    @Select("select * from book")
    Book[] findAllBook();

    Book[] selectAllBookLikeByOpenid(String openid);



}
