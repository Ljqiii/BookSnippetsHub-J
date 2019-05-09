package com.ljqiii.service;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.WxAccountRepository;

import com.ljqiii.model.WxAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
public class WxAccountServiceImpl implements  WxAccountService{


    @Autowired
    RestTemplate restTemplate;

    @Value("${wx.appid}")
    String appid;

    @Value("${wx.secert}")
    String secret;


    public String code2Session(String js_code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("appid", appid);
        params.add("secret", secret);
        params.add("js_code", js_code);
        params.add("grant_type", "authorization_code");

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
        URI uri = uriComponentsBuilder.build().encode().toUri();
        System.out.println(uri.toString());


        String code2SessionString = restTemplate.getForObject(uri, String.class);

        System.out.println(code2SessionString);
        JSONObject code2SessionJson = JSONObject.parseObject(code2SessionString);
        if (code2SessionJson.containsKey("errcode")) {
            return null;
        } else {
            return code2SessionJson.getString("openid");
        }

    }


}
