package com.pamajon.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @RequestMapping("/board/productQnA")
    public String qna(){return "/board/productQnA"; }
    @RequestMapping("/board/productQnAwrite")
    public String qnaWrite(){ return "/board/productQnAWrite"; }
    @RequestMapping("/board/review")
    public String review(){ return "/board/review"; }
    @RequestMapping("/board/reviewWrite")
    public String reviewWrite(){ return "/board/reviewWrite"; }
    @RequestMapping("/board/boardList")
    public String listBoard(){ return "/board/boardList"; }
}
