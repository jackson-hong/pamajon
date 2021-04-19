package com.pamajon.common.interceptor;

import com.pamajon.admin.model.service.AdminService;
import com.pamajon.admin.model.vo.AdminUser;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
@Component
public class AutoLoginIntercepter implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(AutoLoginIntercepter.class);

    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();


        // 로그인이 안된상태를 나타냄.
        if(session.getAttribute("adminUser") == null){

            Cookie loginCookie = WebUtils.getCookie(request , "loginCookie");

            //로그인 쿠키가 존재함.
            if(loginCookie!=null){

                LOGGER.info("Cookie 아이디 ==> "+loginCookie.getValue());

                AdminUser adminUser = adminService.checkUserWithSessionKey(loginCookie.getValue());

                if(adminUser != null){
                    // 저장되어있는 쿠키와 디비에 들어있는 쿠키가 일치함.
                    // 자동로그인 프로세스가 실행되어 자동로그인 하여 admin 메인페이지로 토스
                    session.setAttribute("adminUser",adminUser);
                    response.sendRedirect("/pamajon/admin/mainPage");
                    return false;
                }

                if(adminUser == null){
                    // 일치하지 않을경우  로그인 페이지 로 가게됨.
                    return true;
                }
            }
        }
        //로그인 돼있는상태임.
        if(session.getAttribute("adminUser") != null){

            response.sendRedirect("/pamajon/admin/mainPage");
            return false;

        }


        return true;
    }
}
