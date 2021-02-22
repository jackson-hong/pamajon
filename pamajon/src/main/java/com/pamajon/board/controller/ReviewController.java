package com.pamajon.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board")
public class ReviewController {
    //리뷰 관련
    @RequestMapping("/review")
    public ModelAndView review(ModelAndView mv){
        mv.setViewName("/board/review");
        return mv; }
    @RequestMapping("/review/write")
    public ModelAndView reviewWrite(ModelAndView mv){
        mv.setViewName("/board/reviewWrite");
        return mv; }
    @RequestMapping("/review/view")
    public ModelAndView reviewDetail(ModelAndView mv){
        mv.setViewName("/board/reviewDetail");
        return mv; }
}
