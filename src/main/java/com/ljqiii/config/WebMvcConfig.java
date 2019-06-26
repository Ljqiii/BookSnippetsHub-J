package com.ljqiii.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${xyz.ljqiii.uploadedfilepath}")
    String uploadedfilepath;

    @Value("${xyz.ljqiii.resourcepath}")
    String resourcepath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/test").setViewName("test");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/bookcover/**").addResourceLocations("file://"+resourcepath+"/bookcoverimg/");
        registry.addResourceHandler("/img/**").addResourceLocations("file://"+uploadedfilepath+"/img/");
        registry.addResourceHandler("/avatar/**").addResourceLocations("file://"+uploadedfilepath+"/avatar/");
        registry.addResourceHandler("/bgimg/**").addResourceLocations("file://"+resourcepath+"/bgimg/");
    }
}

