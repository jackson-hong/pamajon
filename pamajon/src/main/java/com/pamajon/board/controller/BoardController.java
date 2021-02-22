package com.pamajon.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board")
public class BoardController {
    //Q&A관련
    @RequestMapping("/qna")
    public ModelAndView qna(ModelAndView mv){
        mv.setViewName("/board/qna");
        return mv;
    }
    @RequestMapping("/qna/write")
    public ModelAndView qnaWrite(ModelAndView mv){
        mv.setViewName("/board/qnaWrite");
        return mv; }
    @RequestMapping("/qna/secret")
    public ModelAndView qnaSecret(ModelAndView mv){
        mv.setViewName("/board/qnaSecret");
        return mv; }

    //비밀글일경우 거치는 페이지
    @RequestMapping("/qna/view")
    public ModelAndView qnaDetail(ModelAndView mv){
        mv.setViewName("/board/qnaDetail");
        return mv; }
    
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
    
    
    //마이페이지
    @RequestMapping("/boardList")
    public ModelAndView listBoard(ModelAndView mv){
       mv.setViewName("/board/boardList");
        return mv; }

}
