package com.pamajon.mallDetail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MallDetailController {

    @GetMapping("/board/detailView")
    public String gotoDetail(){

        return "/detailView/mallDetailView";
    }

}
