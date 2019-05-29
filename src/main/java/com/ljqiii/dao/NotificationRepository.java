package com.ljqiii.dao;


import com.ljqiii.model.Notification;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NotificationRepository {

    @Select("select count(*) from notification")
    int count();

    @Insert("insert into notification(fromopenid, toopenid, msg) values (#{fromopenid}, #{toopenid}, #{msg})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertByNotification(Notification notification);

    @Select("select * from notification where toopenid=#{toopenid} order by id desc")
    Notification[] findByToopenid(String toopenid);

    @Select("select * from notification where id=#{id}")
    Notification findById(int id);

    @Delete("delete from notification where id=#{id} and toopenid=#{toopenid}")
    int delete(@Param("toopenid") String toopenid, @Param("id") int id);

}
