package com.pamajon.mallDetail.controller;

import com.pamajon.mallDetail.model.service.MallDetailSerivce;
import com.pamajon.mallDetail.model.service.MallDetailServiceImpl;
import com.pamajon.order.model.vo.Member;
import com.pamajon.order.model.vo.ProductOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MallDetailController {

    private MallDetailSerivce mallDetailSerivce;

    @Autowired
    public MallDetailController(MallDetailSerivce mallDetailSerivce){
        this.mallDetailSerivce = mallDetailSerivce;

    }
    @GetMapping("/board/detailView")
    public String gotoDetail(){

        return "/detailView/mallDetailView";
    }


}
