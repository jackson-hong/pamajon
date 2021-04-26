package com.pamajon.common;

import com.pamajon.common.comparators.BrandComparator;
import com.pamajon.product.model.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Log4j2
@RestController
public class IndexController {

    @Value("${spring.application.name}")
    String appName;

    @Qualifier("productServiceImpl")
    private final ProductService service;

    public IndexController(ProductService service){this.service = service;}

    @GetMapping("/")
    public ModelAndView index(ModelAndView mv, HttpSession sess){
        List<HashMap> resultList = service.homeBoard();

//        List<HashMap<String,String>> brandList = service.brandList();

//        BrandComparator comp = new BrandComparator("PRO_BRAND_NAME");
//        Collections.sort(brandList, comp);
//        ArrayList<Character> brandChar = new ArrayList<>();
//        for(char i = 65; i < 91; i++){
//            brandChar.add(i);
//        }
//        mv.addObject("brandList",brandList);
//        mv.addObject("brandChar", brandChar);

        mv.setViewName("home");
        mv.addObject("resultList", resultList);
        mv.addObject("appName",appName);
        return mv;
    }


}
