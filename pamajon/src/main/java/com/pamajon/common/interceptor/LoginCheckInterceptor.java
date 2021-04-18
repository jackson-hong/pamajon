package com.pamajon.common.interceptor;

import com.pamajon.member.model.vo.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Log4j2
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Member login=(Member)(request.getSession().getAttribute("loginMember"));
        RedirectAttributes attr = new RedirectAttributesModelMap();
        String contextPath = request.getContextPath();
        if(login==null) {
            String msg = URLEncoder.encode("잘못된 접근입니다.","utf-8");
            String loc = URLEncoder.encode("/member/login","utf-8");
            response.sendRedirect(contextPath + "/common/msg?msg=" + msg + "&loc=" + loc);
            return false;
        }
        return true;
    }
}
