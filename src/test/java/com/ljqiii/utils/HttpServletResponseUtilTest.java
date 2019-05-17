package com.ljqiii.utils;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.model.WxAccount;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class HttpServletResponseUtilTest {


    public static String returnJson(Object object) {

        String jsonString = JSONObject.toJSONString(object);

        return jsonString;

    }


    @Test
    public void returnJson() {

        WxAccount a = new WxAccount("sadf", "sdf");
        a.setAvatarUrl("dsf");
        System.out.println(this.returnJson(a));
//        System.out.println(HttpServletResponseUtil.returnJson(a););
    }


}