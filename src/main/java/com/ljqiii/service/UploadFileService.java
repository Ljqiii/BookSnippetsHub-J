package com.ljqiii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadFileService {

    String basePath;

    @Autowired
    public UploadFileService(@Value("${xyz.ljqiii.uploadedfilepath}") String path) {
        if (path.endsWith("/")) {
            this.basePath = path;
        } else {
            this.basePath = path + "/";
        }
    }


    public String uuidImg(MultipartFile file) throws IOException {
        return uuidFile(file,"img/");
    }


    public String uuidFile(MultipartFile file, String subpath) throws IOException {

        if (!subpath.endsWith("/")) {
            subpath = subpath + "/";
        }
        String tempName = UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename();
        String extnmae = "";

        if (originalFilename.contains(".")) {
            String[] namepart = originalFilename.split("\\.");
            extnmae = namepart[namepart.length - 1];
        }
        if (!extnmae.equals("")) {
            tempName = tempName + "." + extnmae;
        }
        String pathname = basePath +subpath+ tempName;

        File tempFile = new File(pathname);
        file.transferTo(tempFile);

        return tempName;
    }

}
