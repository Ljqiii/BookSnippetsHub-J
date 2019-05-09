package com.ljqiii.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class JsonUtil {
    /**
     * 将JSON字符串转为Java对象
     */
    public static <T> T jsonString2Object(String result, Class<T> clazz) {
        JSONObject jsonObject = JSONObject.parseObject(result);
        return JSONObject.toJavaObject(jsonObject, clazz);
    }


    public static  String Object2String(Object c) {
        return JSONObject.toJSONString(c);
    }

    /**
     * JSON字符串对象解析成java List对象
     */
    public static <T> ArrayList<T> jsonStringList2Object(String resultList, Class<T> clazz) {
        JSONArray jsonArray = JSONArray.parseArray(resultList);  //返回数据解析成JSONArray对象
        return (ArrayList<T>) jsonArray.toJavaList(clazz);    //JSON对象解析成java List对象
    }
}
