package com.pamajon.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
    //Q&A관련
    @RequestMapping("/board/qna")
    public String qna(){return "/board/qna"; }
    @RequestMapping("/board/qna/write")
    public String qnaWrite(){ return "/board/qnaWrite"; }
    @RequestMapping("/board/qna/secret")
    public String qnaSecret(){ return "/board/qnaSecret"; }

    //비밀글일경우 거치는 페이지
    @RequestMapping("/board/qna/view")
    public String qnaDetail(){ return "/board/qnaDetail"; }
    
    //리뷰 관련
    @RequestMapping("/board/review")
    public String review(){ return "/board/review"; }
    @RequestMapping("/board/review/write")
    public String reviewWrite(){ return "/board/reviewWrite"; }
    @RequestMapping("/board/review/view")
    public String reviewDetail(){ return "/board/reviewDetail"; }
    
    
    //마이페이지
    @RequestMapping("/board/boardList")
    public String listBoard(){ return "/board/boardList"; }
}
