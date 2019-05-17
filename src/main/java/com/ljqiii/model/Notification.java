package com.ljqiii.model;

public class Notification {

    int id;
    String fromopenid;
    String toopenid;
    String msg;

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

    public String getToopenid() {
        return toopenid;
    }

    public void setToopenid(String toopenid) {
        this.toopenid = toopenid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
