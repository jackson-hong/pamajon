package com.pamajon.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    @GetMapping("/order/purchase")
    public String gotoPurchase(){

        return "/order/orderform";
    }

    @RequestMapping("/order/addresslist")
    public String gotoAddress()
    {
        return "/order/addressInput";
    }
}
