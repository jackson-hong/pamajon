package com.pamajon.product.controller;


import com.pamajon.member.model.vo.Member;
import com.pamajon.product.model.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

import java.util.HashMap;

@Log4j2
@RestController
@RequestMapping("/product")
@SessionAttributes("loginMember")
public class ProductController {

    @Qualifier("productServiceImpl")
    private final ProductService service;

    public ProductController(ProductService service){this.service = service;}

    @GetMapping("/{product_id}")
    public ModelAndView productDetail(ModelAndView mv, @PathVariable("product_id") int productId){
        mv.addObject("productId", productId);
        mv.setViewName("detailView/mallDetailview");
        return mv;
    }

    @PostMapping("/wishlist")
    public String wishlistInsert(@ModelAttribute("loginMember")Member loginMember, @RequestBody HashMap input){
        String productId = (String)input.get("productId");
        log.info(loginMember);
        log.info(productId);

        HashMap inputMap = new HashMap();
        inputMap.put("usid", loginMember.getUserId());
        inputMap.put("productId", productId);

        //중복확인
        int duplicateFindResult = service.wishDuplicate(inputMap);
        if(duplicateFindResult > 0)return "DUPLICATE";

        //위시리스트 추가
        int result = service.wishInsert(inputMap);

        //추가 실패
        if(result == 0)return "FAIL";

        return "SUCCESS";
    }

    @GetMapping("/list/big-cate/{cateId}")
    public ModelAndView cateList(ModelAndView mv, @PathVariable("cateId") int cateId){

        List<HashMap> resultList = service.selectProductByBig(cateId);

        String title = service.selectBigCateName(cateId);

        mv.addObject("title", title);
        mv.addObject("resultList", resultList);
        mv.setViewName("board/cateList");
        return mv;
    }

    @GetMapping("/list/small-cate/{cateId}")
    public ModelAndView cateSmallList(ModelAndView mv, @PathVariable("cateId") int cateId){

        List<HashMap> resultList = service.selectProductByBig(cateId);

        String title = service.selectBigCateName(cateId);

        mv.addObject("title", title);
        mv.addObject("resultList", resultList);
        mv.setViewName("board/cateList");
        return mv;
    }

    private ModelAndView msg(ModelAndView mv, String msg, String loc){
        mv.addObject("msg",msg);
        mv.addObject("loc",loc);
        mv.setViewName("common/msg");
        return mv;
    }
    private ModelAndView msgWithScr(ModelAndView mv, String msg, String loc, String script){
        mv.addObject("msg",msg);
        mv.addObject("loc",loc);
        mv.addObject("script", script);
        mv.setViewName("common/msg");
        return mv;
    }
}
