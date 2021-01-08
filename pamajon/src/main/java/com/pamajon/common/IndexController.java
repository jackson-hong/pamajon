package com.pamajon.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("appName",appName);
        return "home";
    }
}
