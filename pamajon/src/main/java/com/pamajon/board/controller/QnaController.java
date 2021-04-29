package com.pamajon.board.controller;

import com.pamajon.board.model.service.QnaServiceImpl;
import com.pamajon.board.model.vo.QnaDto;
import com.pamajon.common.security.AES256Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/qna")
public class QnaController {

    private final AES256Util aes256Util;
    private final QnaServiceImpl service;

    public QnaController(AES256Util aes256Util,QnaServiceImpl service) {
        this.aes256Util = aes256Util;
        this.service =service;
    }



    //Q&A관련
    @RequestMapping("/list")
    public ModelAndView qna(ModelAndView mv){
        List<QnaDto> resultList = service.listQna();
        mv.setViewName("/board/qna");
        mv.addObject("resultList", resultList);
        return mv;
    }
    @RequestMapping("/write")
    public ModelAndView qnaWrite(ModelAndView mv){
        mv.setViewName("/board/qnaWrite");
        return mv; }

    @PostMapping("/write")
    public ModelAndView insertQna(ModelAndView mv, @RequestParam Map input, @RequestParam int memberId, HttpServletRequest request){

        log.info(input);
        String qnaTitle = (String)input.get("qnaTitle");
        String qnaContent = (String)input.get("qnaContent");
        String qnaSecretCheck = (String)input.get("qnaSecretCheck");
        String qnaPwd = (String) input.get("qnaPwd");
        log.info("memberId > " + memberId);
        log.info("done with get map > QNA pwd: " + qnaPwd);
        try {
            qnaPwd = aes256Util.encrypt(qnaPwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("Well Encrypt > " + qnaPwd);


        int qnaSecret = qnaSecretCheck.equals("n") ? 0 : 1;

        QnaDto createQna = new QnaDto();

        createQna.setQnaTitle(qnaTitle);
        createQna.setQnaContent(qnaContent);
        createQna.setQnaStatus(qnaSecret);
        createQna.setQnaPwd(qnaPwd);

        log.info("Access to DB");
        log.info("trying to insertQna");
        log.info(">>>>>>>>>>>>>>>");

        int result = service.createQna(createQna);

        if(result==0){
            mv.addObject("msg", "저장되지 않았습니다.");
            mv.addObject("loc", "/qna/write");
            mv.setViewName("common/msg");
            return mv;
        }
        mv.setViewName("redirect:/qna");
        return mv;
    }


    //비밀글일경우 거치는 페이지
    @RequestMapping("/secret")
    public ModelAndView qnaSecret(ModelAndView mv){
        mv.setViewName("/board/qnaSecret");
        return mv; }

    @RequestMapping("/view")
    public ModelAndView qnaDetail(ModelAndView mv, @RequestParam int qnaNo){
        mv.setViewName("/board/qnaDetail");
        return mv; }

    @RequestMapping("/delete")
    public ModelAndView qnaDelete(ModelAndView mv, @RequestParam int qnaNo){
        log.info("proceed Delete > Board No:" + qnaNo +"<");
        return mv;
    }

    @RequestMapping("/edit")
    public ModelAndView qnaModify(ModelAndView mv, @RequestParam int qnaNo){
        log.info("proceed Modify > Board No:" + qnaNo +"<");

        return mv;
    }
}
