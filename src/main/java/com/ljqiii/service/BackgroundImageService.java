package com.ljqiii.service;

import com.alibaba.fastjson.JSONObject;
import com.ljqiii.dao.BackgroundImageRepository;
import com.ljqiii.model.BackgroundImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class BackgroundImageService {


    @Autowired
    BackgroundImageRepository backgroundImageRepository;


    String picturePath;

    @Autowired
    public BackgroundImageService(@Value("${xyz.ljqiii.resourcepath}") String basePath) {
        if (basePath.endsWith("/")) {
            this.picturePath = basePath;
        } else {
            this.picturePath = basePath + "/";
        }
    }


    public JSONObject getAllBackgroundImage() {

        BackgroundImage[] backgroundImages = backgroundImageRepository.findAll();

        JSONObject allbackgroundImages = new JSONObject();

        ArrayList<JSONObject> temp = new ArrayList<>();

        for (int i = 0; i < backgroundImages.length; i++) {
            JSONObject j = new JSONObject();
            j.put("id", backgroundImages[i].getId());
            j.put("name", backgroundImages[i].getName());
            j.put("uri", "/bgimg/" + backgroundImages[i].getFilename());
            j.put("description", backgroundImages[i].getDescription());

            temp.add(j);
        }

        allbackgroundImages.put("errcode", 0);
        allbackgroundImages.put("backgroundImages", temp);

        return allbackgroundImages;
    }


}
