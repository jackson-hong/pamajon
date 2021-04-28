package com.pamajon.common;

import com.pamajon.common.interceptor.HeaderInterceptor;
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

    @Autowired
    @Qualifier(value = "HeaderInterceptor")
    private HeaderInterceptor headerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor)
                .addPathPatterns("/member**")
                .addPathPatterns("/member/*")
                .addPathPatterns("/order/cart")
                .excludePathPatterns("/member/")
                .excludePathPatterns("/member/login")
                .excludePathPatterns("/member/kakao**")
                .excludePathPatterns("/member/idCheck")
                .excludePathPatterns("/member/login/**")
                .excludePathPatterns("/member/join")
                .excludePathPatterns("/member/address")
                .excludePathPatterns("/member/address*");
        registry.addInterceptor(headerInterceptor)
                .addPathPatterns("/")
                .addPathPatterns("/*")
                .addPathPatterns("/**");
    }
}
