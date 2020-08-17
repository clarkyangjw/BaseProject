package com.clark.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/user/user-login");
        registry.addViewController("/user-login.html").setViewName("/user/user-login");
        registry.addViewController("/user-register.html").setViewName("/user/user-register");
        registry.addViewController("/index.html").setViewName("/index");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("static/**",
                        "/error/**",
                        "/",
                        "/login",
                        "/register",
                        "/addUser"

                );
    }

}
