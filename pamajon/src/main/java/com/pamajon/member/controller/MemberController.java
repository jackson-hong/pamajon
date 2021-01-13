package com.pamajon.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MemberController {

    @RequestMapping("/member/myPage")
    public String myPage(){
        return "/member/myPage";
    }

    @RequestMapping("/member/login")
    public String login(){
        return "/member/login";
    }

    @RequestMapping("/member/join")
    public String join(){
        return "/member/join";
    }
}
