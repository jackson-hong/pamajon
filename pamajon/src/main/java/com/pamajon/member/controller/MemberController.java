package com.pamajon.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@RestController
public class MemberController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

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
        logger.debug("???????");
        logger.debug(""+inputs);

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
