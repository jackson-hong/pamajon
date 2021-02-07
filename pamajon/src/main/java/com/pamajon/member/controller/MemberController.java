package com.pamajon.member.controller;

import com.pamajon.member.model.service.MemberService;
import com.pamajon.member.model.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Qualifier("memberServiceImpl")
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @RequestMapping("/myPage")
    public ModelAndView myPage(ModelAndView mv){
        mv.setViewName("/member/myPage");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView mv){

        mv.setViewName("/member/login");
        return mv;
    }

    @GetMapping("/join")
    public ModelAndView join(ModelAndView mv){
        mv.setViewName("/member/join");
        return mv;
    }

    @RequestMapping("/insert")
    public ModelAndView joinEnd(ModelAndView mv, @RequestParam Map inputs) {

        service.memberInsert(inputs);

        mv.setViewName("member/myPage");
        return mv;
    }

    @RequestMapping("/orderList")
    public ModelAndView orderList(ModelAndView mv) {
        mv.setViewName("/member/orderList");
        return mv;
    }

    @RequestMapping("/modify")
    public ModelAndView modify(ModelAndView mv){
        mv.setViewName("/member/modify");
        return mv;
    }

    @RequestMapping("/wishList")
    public ModelAndView wishList(ModelAndView mv){
        mv.setViewName("/member/wishList");
        return mv;
    }

    @RequestMapping("/mileage")
    public ModelAndView mileage(ModelAndView mv){
        mv.setViewName("/member/mileage");
        return mv;
    }

    @RequestMapping("/address")
    public ModelAndView address(ModelAndView mv){
        mv.setViewName("/member/address");
        return mv;
    }
}
