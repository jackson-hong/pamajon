package com.pamajon.order.controller;

import com.pamajon.order.model.service.OrderService;
import com.pamajon.order.model.vo.Member;
import com.pamajon.order.model.vo.ProductOptionDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {

    @Qualifier("orderServiceImpl")
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/order/purchase")
    public String gotoPurchase(Model model){

        Member m = orderService.getMember(1);
        model.addAttribute("member",m);
        model.addAttribute("email",m.getMemberEmail().split("@"));
        model.addAttribute("mileage",orderService.getMileage(1));

        return "/order/orderform";
    }

    @RequestMapping("/order/addresslist")
    public String gotoAddress()
    {

        return "/order/addressInput";
    }
}
