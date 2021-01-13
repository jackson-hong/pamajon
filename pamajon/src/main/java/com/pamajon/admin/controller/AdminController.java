package com.pamajon.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String gotoAdmin(){

        return"admin/adminMainPage";

    }
    @GetMapping("/bResgistration")
    public String gotoResgistration(){

        return "admin/profile";
    }

    @GetMapping("/memberManager")
    public String gotoMemberHandler(){

        return "admin/memberHandler";
    }

    @GetMapping("/productManager")
    public String gotoProductHandler(){

        return "admin/productHandler";
    }





}
