package com.pamajon.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/order/purchase")
    public String gotoPurchase(){

        return "/order/orderform";
    }
}
