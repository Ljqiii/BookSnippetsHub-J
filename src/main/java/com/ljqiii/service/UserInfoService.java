package com.ljqiii.service;


import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.WxAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserInfoService {

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    WxAccountRepository wxAccountRepository;

    public String changAvatar(WxAccount wxAccount, MultipartFile multipartFile) throws IOException {
        String a = uploadFileService.uuidFile(multipartFile, "avatar/");
        wxAccount.setAvatarUrl("/avatar/" + a);
        wxAccountRepository.updateByWxAccount(wxAccount);
        return a;
    }


}
