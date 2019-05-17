package com.ljqiii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BackgroundPictureService {


    String picturePath;

    @Autowired
    public BackgroundPictureService(@Value("${xyz.ljqiii.resourcepath}") String basePath) {
        if (basePath.endsWith("/")) {
            this.picturePath = basePath;
        } else {
            this.picturePath = basePath + "/";
        }
    }



}
