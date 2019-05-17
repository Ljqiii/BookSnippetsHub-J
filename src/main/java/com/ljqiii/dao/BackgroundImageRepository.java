package com.ljqiii.dao;


import com.ljqiii.model.BackgroundImage;
import com.ljqiii.model.WxAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface BackgroundImageRepository {

    @Select("select count(*) from BackgroundImage")
    int count();

    @Select("select * from BackgroundImage where id=#{id} limit 1")
    BackgroundImage findById(int id);

    @Select("select * from BackgroundImage where name=#{name} limit 1")
    BackgroundImage findByName(String name);

    @Select("select * from BackgroundImage")
    BackgroundImage[] findAll();

    @Insert("insert into BackgroundImage (name,filename,description) value(#{name},#{filename},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByBackgroundImage(BackgroundImage backgroundImage);


}
