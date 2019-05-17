package com.ljqiii.model;

import java.util.Date;

public class Feed {
    int id;

    String fromopenid;
    String openid;
    int backgroundimageid;
    int bookid;
    String bookcontent;
    String bookcomment;
    Date feedtime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromopenid() {
        return fromopenid;
    }

    public void setFromopenid(String fromopenid) {
        this.fromopenid = fromopenid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getBackgroundimageid() {
        return backgroundimageid;
    }

    public void setBackgroundimageid(int backgroundimageid) {
        this.backgroundimageid = backgroundimageid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookcontent() {
        return bookcontent;
    }

    public void setBookcontent(String bookcontent) {
        this.bookcontent = bookcontent;
    }

    public String getBookcomment() {
        return bookcomment;
    }

    public void setBookcomment(String bookcomment) {
        this.bookcomment = bookcomment;
    }

    public Date getFeedtime() {
        return feedtime;
    }

    public void setFeedtime(Date feedtime) {
        this.feedtime = feedtime;
    }

}
