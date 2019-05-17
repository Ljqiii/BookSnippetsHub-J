package com.ljqiii.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletResponseUtil {

    public static void returnString(HttpServletResponse httpServletResponse, String jsonString) throws IOException {

        httpServletResponse.setHeader("content-type", "application/json");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(jsonString);
        out.flush();
        out.close();
    }

    public static void returnJson(HttpServletResponse httpServletResponse, Object object) throws IOException {

        String jsonString = JSONObject.toJSONString(object);
        httpServletResponse.setHeader("content-type", "application/json");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(jsonString);
        out.flush();
        out.close();

    }
    public static void returnJson(HttpServletResponse httpServletResponse, JSONObject jsonObject) throws IOException {

        String jsonString = jsonObject.toJSONString();
        httpServletResponse.setHeader("content-type", "application/json");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(jsonString);
        out.flush();
        out.close();

    }


}
