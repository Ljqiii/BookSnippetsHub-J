package com.ljqiii;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestTempleTest {


    String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx17694ab90179c756&secret=9c98052a0b26d19f6a98a83f004aad9a&js_code=021YHTXZ1jM0GU0CFuUZ1UyYXZ1YHTXL&grant_type=authorization_code";

    @Test
    public void test1() {

        SimpleClientHttpRequestFactory clientHttpRequestFactory=new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(60000);
        clientHttpRequestFactory.setReadTimeout(1000);
        RestTemplate restTemplat=new RestTemplate(clientHttpRequestFactory);
//<200,{"errcode":40029,"errmsg":"invalid code, hints: [ req_id: OINBZ8yFe-r_343 ]"},[Connection:"keep-alive", Content-Type:"text/plain", Date:"Sun, 28 Apr 2019 10:30:00 GMT", Content-Length:"77"]>        List a=restTemplat.getMessageConverters();

        ResponseEntity<String> s=restTemplat.getForEntity(url,String.class);
        HttpStatus statuscode=s.getStatusCode();
        int v=s.getStatusCodeValue();
        HttpHeaders headers=s.getHeaders();
        String body=s.getBody();

        JSONObject jsonObject=JSONObject.parseObject(body);

        if(jsonObject.containsKey("errcode")){

        }

//        System.out.println(code2Session.toString());
        System.out.println(s);



    }

}
