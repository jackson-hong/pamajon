package com.pamajon.common;

import com.pamajon.product.model.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Log4j2
@RestController
public class IndexController {

    @Value("${spring.application.name}")
    String appName;

    @Qualifier("boardServiceImpl")
    private final ProductService service;

    public IndexController(ProductService service){this.service = service;}

    @GetMapping("/")
    public ModelAndView index(ModelAndView mv){
        List<HashMap> resultList = service.homeBoard();
        log.info(resultList);
        mv.setViewName("home");
        mv.addObject("resultList", resultList);
        mv.addObject("appName",appName);
        return mv;
    }
}
