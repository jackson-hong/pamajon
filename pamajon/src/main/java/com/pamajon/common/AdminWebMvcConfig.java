package com.pamajon.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("adminCheckInterceptor")
    private HandlerInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/signin")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/mainPage");
    }
}
