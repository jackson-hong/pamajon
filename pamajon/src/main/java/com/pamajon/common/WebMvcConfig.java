package com.pamajon.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "loginCheckInterceptor")
    private HandlerInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor)
                .addPathPatterns("/member/**")
                .excludePathPatterns("/member/login")
                .excludePathPatterns("/member/login/**")
                .excludePathPatterns("/member/join");
    }
}
