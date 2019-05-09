package com.ljqiii.controller;


import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.WxAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class test {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WxAccountRepository wxAccountRepository;

    @Value("${wx.appid}")
    private String appName;


    @Autowired
    WxAccountServiceImpl wxAccountService;


    @RequestMapping(value = "/testa", method = RequestMethod.GET)
    public String testa(HttpServletRequest httpServletRequest) throws IOException {

        WxAccountServiceImpl a=wxAccountService;




        return appName;
//        return stringBuilder.toString();


    }

    @RequestMapping(value = "/testinsert", method = RequestMethod.GET)
    public String insert() {
        WxAccount wxAccount = new WxAccount("11", "111");

        wxAccountRepository.insertOneByWxAccount(wxAccount);
        System.out.println(wxAccount.getId());
        return "2q";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String test() {
        System.out.println("test controller");
        return "[1,23]";
    }

    @RequestMapping(value = "/t2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public WxAccount test2() {

        Map<String, String> map = new HashMap<>();

        WxAccount a = restTemplate.getForObject("http://127.0.0.1:5000/t", WxAccount.class);

        System.out.println("test controller");
        WxAccount u = new WxAccount("1", "2");

        u.setId(1);
        u.setOpenId("op");
        u.setSessionKey("sadf");

        return u;
    }

    @RequestMapping("/test3")
    public String aa(Model model) {
        model.addAttribute("a", "aa");

        return "index";
    }


}
