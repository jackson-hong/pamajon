package com.pamajon.board.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/qna")
public class QnaController {

    //Q&A관련
    @RequestMapping("/")
    public ModelAndView qna(ModelAndView mv){
        mv.setViewName("/board/qna");
        return mv;
    }
    @RequestMapping("/write")
    public ModelAndView qnaWrite(ModelAndView mv){
        mv.setViewName("/board/qnaWrite");
        return mv; }

    @PostMapping("/write")
    public ModelAndView insertQna(ModelAndView mv, @RequestParam Map input, HttpServletRequest request){
        log.info("well done");
        log.info(input);

        return mv;
    }
    @RequestMapping("/secret")
    public ModelAndView qnaSecret(ModelAndView mv){
        mv.setViewName("/board/qnaSecret");
        return mv; }

    //비밀글일경우 거치는 페이지
    @RequestMapping("/view")
    public ModelAndView qnaDetail(ModelAndView mv){
        mv.setViewName("/board/qnaDetail");
        return mv; }
}
