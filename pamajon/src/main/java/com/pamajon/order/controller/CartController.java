package com.pamajon.order.controller;

import com.pamajon.member.model.vo.Member;
import com.pamajon.order.model.service.CartService;
import com.pamajon.order.model.service.OrderService;
import com.pamajon.order.model.vo.CartDto;
import com.pamajon.order.model.vo.CartInsert;
import com.pamajon.order.model.vo.CartListDto;
import com.pamajon.order.model.vo.ProductOptionDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;

@Log4j2
@RestController
@SessionAttributes("loginMember")
public class CartController {

    CartService cartService;
    OrderService orderService;

    @Autowired
    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostMapping("/order/cart")
    public String cartInsert(ModelAndView mv, @RequestBody HashMap input, HttpSession sess){
        ArrayList<CartDto> optionList = new ArrayList<CartDto>();
        ArrayList<HashMap> inputList = (ArrayList<HashMap>)input.get("optionArr");

        inputList.forEach(option -> {
            CartDto dto = new CartDto();
            dto.setPOptionId(Integer.parseInt((String)option.get("optionId")));
            dto.setOptionQuantity(Integer.parseInt((String)option.get("optionQuantity")));
        });

        Optional<Member> member = Optional.ofNullable((Member) sess.getAttribute("loginMember"));
        try {
            cartService.cartInsert(optionList, member);
        }catch(IllegalArgumentException e){
            return "LOGINNEED";
        }
        return "SUCCESS";
    }

    @GetMapping("/order/cart")
    public ModelAndView goToCartList(ModelAndView mv, @ModelAttribute("loginMember") Member loginMember) {
        int userId = loginMember.getUserId();
        List<CartListDto> cartList = cartService.cartList(userId);

        log.info(cartList);
        mv.addObject("count", cartList.size());
        mv.addObject("cartList", cartList);
        mv.setViewName("/order/cartList");
        return mv;
    }
}
