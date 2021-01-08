package com.pamajon.mybatisTest.controller;

import com.pamajon.mybatisTest.model.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CityController {

    @Autowired
    private CityService cService;

    @GetMapping(value = "/cites")
    @ResponseBody
    public String selectCities(){
        System.out.println(cService.selectCities());
        return "1";
    }

}
