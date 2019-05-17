package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.BackgroundImageRepository;
import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.BackgroundImage;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.BackgroundImageService;
import com.ljqiii.service.UploadFileService;
import com.ljqiii.service.WxAccountServiceImpl;
import org.apache.ibatis.javassist.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class test {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WxAccountRepository wxAccountRepository;

    @Value("${wx.appid}")
    private String appName;

    @Autowired
    BackgroundImageRepository backgroundImageRepository;

    @Autowired
    BackgroundImageService backgroundImageService;

    @Autowired
    WxAccountServiceImpl wxAccountService;


    @Autowired
    UploadFileService uploadFileService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public JSONObject testa(HttpServletRequest httpServletRequest) throws IOException {
        return backgroundImageService.getAllBackgroundImage();
    }

    @PostMapping("/testupload")
    public String testupload(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadFileService.uuidImg(file);
    }
    @GetMapping("/testuser")
    public String tu(Principal principal){
        int a=1;
        return principal.toString();
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
