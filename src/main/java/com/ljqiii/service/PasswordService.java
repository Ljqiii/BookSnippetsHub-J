package com.ljqiii.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {


    BCryptPasswordEncoder bCryptPasswordEncoder;


    public PasswordService() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String encode(CharSequence rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }


    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
