package com.pamajon.warningpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WarningPageController {

    @RequestMapping("/warning")
    public String sendToWarningPage(){

        return "warningPage/warningPage";
    }
}
