package com.pamajon.common.interceptor;

import com.pamajon.member.model.vo.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Log4j2
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Member login=(Member)(request.getSession().getAttribute("loginMember"));

        String msg = "";
        String contextPath = request.getContextPath();
        if(login==null) {
            response.sendRedirect(contextPath+"/member/login");
        }
        return true;
    }
}
