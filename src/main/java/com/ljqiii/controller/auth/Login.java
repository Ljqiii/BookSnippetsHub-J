package com.ljqiii.controller.auth;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.JwtTokenService;
import com.ljqiii.service.PasswordService;
import com.ljqiii.service.UploadFileService;
import com.ljqiii.service.UploadUserInfo;
import com.ljqiii.utils.AccountTypeUtil;
import com.ljqiii.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class Login {


    @Autowired
    PasswordService passwordService;

    @Autowired
    WxAccountRepository wxAccountRepository;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    UploadUserInfo uploadUserInfo;

    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/auth/wxlogin")
    public JSONObject wxLogin(WxAuthenticationToken wxAuthenticationToken) {
        WxAccount wxAccount = (WxAccount) wxAuthenticationToken.getPrincipal();

        String token = (String) wxAuthenticationToken.getCredentials();

        JSONObject jsonrestlt = new JSONObject();
        jsonrestlt.put("errcode", "0");
        jsonrestlt.put("token", token);
        jsonrestlt.put("userid", wxAccount.getId());


        if (wxAccount.getNickName() == null) {
            jsonrestlt.put("hasuserinfo", false);
        } else {
            jsonrestlt.put("hasuserinfo", true);
        }
        return jsonrestlt;
    }


    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/auth/uploadWxUserinfo")
    public String uploadWxUserinfo(@RequestBody JSONObject userinfo, WxAuthenticationToken wxAuthenticationToken) throws IOException {
        uploadUserInfo.upload((Integer) wxAuthenticationToken.getCredentials(), userinfo);
        return "ok";
    }

    @PreAuthorize("hasAuthority('ROLE_WXUSER')")
    @PostMapping("/auth/uploadAvatar")
    public String uploadAvatar(WxAuthenticationToken wxAuthenticationToken, MultipartFile file) {
        return "";
    }


    @PostMapping("/restlogin")
    public JSONObject restLogin(@RequestBody JSONObject requestjson) {
        String nickname = requestjson.getString("nickname");
        String password = requestjson.getString("password");

        JSONObject responejson=new JSONObject();

        List<WxAccount> wxAccounts = wxAccountRepository.selectByNickName(nickname);

        int sumUuidAccount = 0;
        int index = -1;
        for (WxAccount wxAccount : wxAccounts) {
            index++;
            if (AccountTypeUtil.judge(wxAccount.getOpenId()) == AccountTypeUtil.UUIDACCOUNT) {
                sumUuidAccount++;
            }
        }

        if (sumUuidAccount == 1) {
            if (passwordService.matches(password,wxAccounts.get(index).getEncodedPassword())) {
                responejson.put("errcode",0);
                responejson.put("errmsg","");
                String token=jwtTokenService.generateToken(wxAccounts.get(index).getUsername());
                responejson.put("token",token);

                return responejson;
            }else {
                responejson.put("errcode",1);
                responejson.put("errmsg","用户名或密码错误");

                return responejson;
            }
        }else if(sumUuidAccount==0){
            responejson.put("errcode",1);
            responejson.put("errmsg","用户不存在");

            return responejson;
        }
        responejson.put("errcode",1);
        responejson.put("errmsg","内部错误");//出现了同名用户
        return responejson;
    }
}
