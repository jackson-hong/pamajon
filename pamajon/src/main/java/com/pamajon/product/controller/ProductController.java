package com.pamajon.product.controller;


import com.pamajon.common.page.PageFactory;
import com.pamajon.member.model.vo.Member;
import com.pamajon.product.model.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.image.AreaAveragingScaleFilter;
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

    @GetMapping("/{cateClass}/{cateId}")
    public ModelAndView cateList(ModelAndView mv,
                                 @PathVariable("cateClass") String cateClass,
                                 @PathVariable("cateId") int cateId,
                                 @RequestParam(defaultValue = "1") int cPage){

        String title = "";
        List<HashMap> resultList = new ArrayList<>();
        switch (cateClass){
            case "big-cate":{
                //타이틀 받아오기.
                title = service.selectBigCateName(cateId);
                //리스트 받아오기
                resultList = service.selectProductByBig(cateId);
                break;
            }
            case "small-cate":{
                title = service.selectSmallCateName(cateId);
                resultList = service.selectProductBySmall(cateId);
                break;
            }
            case "brand":{
                title = service.selectBrandName(cateId);
                resultList = service.selectBrand(cateId);
                break;
            }
            case "new-arrival":{
                title = "NEW ARRIVAL";
                resultList = service.newArrival();
                break;
            }
        }


        int leng = resultList.size();
        resultList = resultList.subList((cPage-1)*10, cPage*10 > leng ? leng : cPage*10);

        String pageBar = PageFactory.getPageBar(leng,cPage,10);

        mv.addObject("title", title);
        mv.addObject("pageBar", pageBar);
        mv.addObject("cateId", cateId);
        mv.addObject("cate", cateClass);
        mv.addObject("resultList", resultList);
        mv.setViewName("product/cateList");
        return mv;
    }

    @GetMapping("/search")
    public ModelAndView productSearch(ModelAndView mv, @RequestParam("keyword") String key, @RequestParam(defaultValue = "1") int cPage){

        List<HashMap> resultList = service.productSearch(key);
        String title = key;

        int leng = resultList.size();
        resultList = resultList.subList((cPage-1)*10, cPage*10 > leng ? leng : cPage*10);

        String pageBar = PageFactory.getPageBar(leng,cPage,10);

        mv.addObject("title", title);
        mv.addObject("pageBar", pageBar);
        mv.addObject("resultList", resultList);
        mv.addObject("keyword",key);
        mv.setViewName("product/searchList");

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
