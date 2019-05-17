package com.ljqiii.config.security.GrantedAuthority;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WxAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    public WxAuthenticationToken(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    public WxAuthenticationToken(Object principal, Object credentials) {
        super(null);

        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false); // must use super, as we override
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

}
