package com.ljqiii.controller;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.ljqiii.service.BackgroundImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackgroundImageController {

    @Autowired
    BackgroundImageService backgroundImageService;


    @GetMapping("/getallbackgroundimage")
    public JSONObject getallbackgroundimage() {
        return backgroundImageService.getAllBackgroundImage();
    }



}
