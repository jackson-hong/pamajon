package com.pamajon.common;

import com.pamajon.common.filter.UrlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean getFilterRegistrationBean(){
//
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new UrlFilter());
//        registrationBean.addUrlPatterns("/order/*");
//
//        return registrationBean;
//    }

}
