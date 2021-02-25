package com.pamajon.board.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board")
public class BoardController {
    //마이페이지
    @RequestMapping("/boardList")
    public ModelAndView listBoard(ModelAndView mv){
        mv.setViewName("/board/boardList");
        return mv; }
}
