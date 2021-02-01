package com.pamajon.member.controller;

import com.pamajon.member.model.service.MemberService;
import com.pamajon.member.model.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MemberController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Qualifier("memberService")
    private final MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @RequestMapping("/myPage")
    public String myPage(){
        return "/member/myPage";
    }

    @RequestMapping("/login")
    public String login(){
        return "/member/login";
    }

    @GetMapping("/member/join")
    public ModelAndView join(ModelAndView mv){
        mv.setViewName("/member/join");
        return mv;
    }

    @RequestMapping("/member/insert")
    public ModelAndView joinEnd(ModelAndView mv, @RequestParam Map inputs) {
        logger.info("???????");
        logger.info(""+inputs);
        service.memberInsert(inputs);


        mv.setViewName("member/myPage");
        return mv;
    }

    @RequestMapping("/orderList")
    public String orderList() {
        return "member/orderList";
    }

    @RequestMapping("/modify")
    public String modify(){
        return "member/modify";
    }

    @RequestMapping("/wishList")
    public String wishList(){
        return "member/wishList";
    }

    @RequestMapping("/mileage")
    public String mileage(){
        return "member/mileage";
    }

    @RequestMapping("/address")
    public String address(){
        return "member/address";
    }
}
