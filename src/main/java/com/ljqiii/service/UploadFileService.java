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

    String uploadFileBasePath;
    String userResourceBasePath;

    @Autowired
    public UploadFileService(@Value("${xyz.ljqiii.uploadedfilepath}") String path, @Value("${xyz.ljqiii.userresource}") String path2) {

        if (path.endsWith("/")) {
            this.uploadFileBasePath = path;
        } else {
            this.uploadFileBasePath = path + "/";
        }

        if (path2.endsWith("/")) {
            this.userResourceBasePath = path;
        } else {
            this.userResourceBasePath = path + "/";
        }

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
        String pathname = uploadFileBasePath + subpath + tempName;

        File tempFile = new File(pathname);
        file.transferTo(tempFile);

        return tempName;
    }


    public boolean uploadFile(MultipartFile file, String basePath, String subpath, boolean overwrite) throws IOException {

        if (!subpath.endsWith("/")) {
            subpath = subpath + "/";
        }
        String originalFilename = file.getOriginalFilename();
        String pathname = basePath + subpath + originalFilename;

        File tempFile = new File(pathname);

        if (overwrite != true) {
            if (tempFile.exists()) {
                return false;
            }
        }
        file.transferTo(tempFile);

        return true;
    }


}
