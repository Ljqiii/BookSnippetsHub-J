package com.ljqiii.controller.auth;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @PostMapping("/auth/wxlogin")
    public String Login(String js_code) {
        System.out.println("===============\"===============\"===============");
        return "login";

    }

}
