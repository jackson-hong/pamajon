package com.pamajon.common.interceptor;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
@Component
public class AdminCheckInterceptor implements HandlerInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(AdminCheckInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getHeader("REFERER") == null && request.getSession().getAttribute("adminUser") == null){
            LOGGER.info("관리자 권한이 아닌 사람이 url 직접접근시 메인페이지로 redirect");
            response.sendRedirect("/warning");
            return false;
        }
        if(request.getHeader("REFERER") == null && request.getSession().getAttribute("adminUser") != null){

            LOGGER.info("관리자 권한이 있지만 url 직접접근시 메인페이지로 redirect");
            HttpSession session = request.getSession();
            session.setAttribute("alarm","잘못된 접근 방법입니다.");
            session.setMaxInactiveInterval(5);
            response.sendRedirect("/admin/mainPage");
            return false;
        }
        if(request.getHeader("REFERER") != null && request.getSession().getAttribute("adminUser") != null){
            return true;
        }
        response.sendRedirect("/warning");
        return false;
    }

}
