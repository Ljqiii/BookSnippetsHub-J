package com.ljqiii.config.security.GrantedAuthority;

import org.springframework.security.core.GrantedAuthority;

public class WxUser implements GrantedAuthority {
    String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public WxUser() {
        this.authority="wxuser";
    }

    public WxUser(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
