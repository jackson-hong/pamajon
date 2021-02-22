package com.pamajon.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/board")
public class QnaController {

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
}
