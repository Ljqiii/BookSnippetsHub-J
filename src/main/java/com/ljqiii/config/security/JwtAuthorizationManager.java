package com.ljqiii.config.security;

import com.ljqiii.config.security.BadJsCodeException;
import com.ljqiii.config.security.GrantedAuthority.WxUser;
import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.WxAccountService;
import com.ljqiii.service.WxAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;


@Component
public class JwtAuthorizationManager implements AuthenticationManager {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    WxAccountService wxAccountService;

    @Autowired
    WxAccountRepository wxAccountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("in JwtAuthorizationManager");

        String js_code = (String) authentication.getCredentials();
        System.out.println("in JwtAuthorizationManager: "+js_code);


        String openid = wxAccountService.code2Session(js_code);

        if (openid == null) {
            throw new BadJsCodeException("verify fail");
        } else {
            WxAccount wxAccount = wxAccountRepository.findByOpenid(openid);
            if (wxAccount == null) {
                wxAccount = new WxAccount(openid, "");
                wxAccountRepository.insertOneByWxAccount(wxAccount);
            }

            ArrayList<WxUser> grantedAuthoritys = new ArrayList<WxUser>();
            grantedAuthoritys.add(new WxUser());

            return new UsernamePasswordAuthenticationToken(wxAccount, openid, grantedAuthoritys);
        }

    }
}
