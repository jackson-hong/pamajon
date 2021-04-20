package com.pamajon.board.controller;


import com.pamajon.board.model.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@RestController
@RequestMapping("/board")
public class ProductController {

    @Qualifier("boardServiceImpl")
    private final ProductService service;

    public ProductController(ProductService service){this.service = service;}

}
