package com.ljqiii.config.security;

import org.springframework.security.core.AuthenticationException;

public class BadJsCodeException extends AuthenticationException {
    public BadJsCodeException(String msg) {
        super(msg);
    }


    public BadJsCodeException(String msg, Throwable t) {
        super(msg, t);
    }
}
