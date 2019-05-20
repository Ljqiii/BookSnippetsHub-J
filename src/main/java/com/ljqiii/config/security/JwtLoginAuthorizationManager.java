package com.ljqiii.config.security;

import com.ljqiii.config.security.GrantedAuthority.WxAuthenticationToken;
import com.ljqiii.dao.WxAccountRepository;
import com.ljqiii.model.WxAccount;
import com.ljqiii.service.JwtTokenService;
import com.ljqiii.service.NotificationService;
import com.ljqiii.service.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Component
public class JwtLoginAuthorizationManager implements AuthenticationManager {

    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    WxAccountService wxAccountService;

    @Autowired
    WxAccountRepository wxAccountRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    NotificationService notificationService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("in JwtAuthorizationManager");

        String js_code = (String) authentication.getCredentials();
        System.out.println("in JwtAuthorizationManager: " + js_code);


        String openid = wxAccountService.code2Session(js_code);

        if (openid == null) {
            throw new BadJsCodeException("verify fail");
        } else {

              UserDetails wxAccount = wxAccountRepository.findByOpenid(openid);
            if (wxAccount == null) {
                notificationService.insertSystemNotification(openid,"欢迎来到读书分享");
                wxAccountRepository.insertOneByWxAccount(new WxAccount(openid, ""));
                wxAccount = wxAccountRepository.findByOpenid(openid);
            }

            ArrayList<SimpleGrantedAuthority> grantedAuthoritys = new ArrayList<>();
            grantedAuthoritys.add(new SimpleGrantedAuthority("ROLE_WXUSER"));

            String jwt_token = jwtTokenService.generateToken(wxAccount.getUsername());

            WxAuthenticationToken authenticationToken = new WxAuthenticationToken(wxAccount, jwt_token, grantedAuthoritys);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            return authenticationToken;
        }

    }
}
