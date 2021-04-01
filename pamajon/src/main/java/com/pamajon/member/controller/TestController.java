package com.pamajon.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TestController {

    @PostMapping(value = "kakao")
    public String test(@RequestBody Map input) {

        return "???";
    }
}
