package com.pamajon.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pamajon.member.model.vo.Member;
import com.pamajon.order.model.service.CartService;
import com.pamajon.order.model.service.OrderService;
import com.pamajon.order.model.vo.CartDto;
import com.pamajon.order.model.vo.CartInsert;
import com.pamajon.order.model.vo.CartListDto;
import com.pamajon.order.model.vo.ProductOptionDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.mapper.Mapper;
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

        log.info(inputList);
        inputList.forEach(option -> {
            CartDto dto = new CartDto();
            dto.setPOptionId(Integer.parseInt((String)option.get("optionId")));
            dto.setOptionQuantity(Integer.parseInt((String)option.get("optionQuantity")));
            optionList.add(dto);
        });
        log.info(optionList);

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

        int priceSum = cartList.stream().mapToInt(cart -> {return Integer.parseInt(cart.getProductPrice()) * cart.getOptionQuantity();}).sum();

        int delivery = 0;

        if(priceSum < 50000) delivery = 3000;

        int totalPrice = 0;

        totalPrice = priceSum + delivery;


        mv.addObject("priceSum", priceSum);
        mv.addObject("totalPrice",totalPrice);
        mv.addObject("delivery", delivery);
        mv.addObject("count", cartList.size());
        mv.addObject("cartList", cartList);
        mv.setViewName("/order/cartList");
        return mv;
    }

    @PutMapping("/order/cart/{input}")
    public String modifyCart(@PathVariable("input") String input){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> map = new HashMap<>();
        try {
            map = mapper.readValue(input, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(map.isEmpty()) return "INVALID";

        cartService.cartModify(map);

        return "success";
    }

    @DeleteMapping("/order/cart/{input}")
    public String wishListDelete(@PathVariable("input") String input, @ModelAttribute("loginMember") Member loginMember){
        log.info(input);

        //JSON -> Map
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        try {
            map = mapper.readValue(input, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //맵에서 밸류만 가져오기
        Collection list = map.values();

        //user id
        int usid = loginMember.getUserId();

        log.info(list);

        int result = 0;
        for (Object s : list) {
            if(s instanceof Integer) s = String.valueOf(s);
            HashMap wishMap = new HashMap();
            wishMap.put("usid", usid);
            wishMap.put("sbId", s);
            result += cartService.cartDelete(wishMap);
        }

        if(result == 0) return "error";

        return "success";
    }

    @GetMapping("/order/option")
    public ModelAndView setOption(ModelAndView mv,
                                  @ModelAttribute("loginMember") Member loginMember,
                                  @RequestParam int productId){
        int usid = loginMember.getUserId();

        List<Map> resultMap = cartService.setOption(productId);

        HashMap example = new HashMap();
        example.put("productName", resultMap.get(0).get("PRODUCT_NAME"));
        example.put("productPrice", resultMap.get(0).get("PRODUCT_PRICE"));

        mv.addObject("exam", example);
        mv.addObject("imgName", resultMap.get(0).get("PRO_IMG_NAME"));
        mv.addObject("userId", usid);
        mv.addObject("resultMap", resultMap);
        mv.setViewName("/order/setOption");

        return mv;
    }
}
