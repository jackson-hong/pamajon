package com.pamajon.board.controller;

import com.pamajon.board.model.dao.QnaDao;
import com.pamajon.board.model.service.QnaServiceImpl;
import com.pamajon.board.model.vo.QnaDto;
import com.pamajon.common.security.AES256Util;
import com.pamajon.member.model.vo.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/qna")
public class QnaController {

    private final AES256Util aes256Util;
    private final QnaServiceImpl qnaService;

    public QnaController(AES256Util aes256Util,QnaServiceImpl service) {
        this.aes256Util = aes256Util;
        this.qnaService =service;
    }



    //Q&A리스트 받아오기
    @GetMapping("/list")
    public ModelAndView qna(ModelAndView mv, HttpServletRequest request){
        List<QnaDto> resultList = qnaService.listQna();
        List<String> writerNames = null;

        for(QnaDto qnaDto : resultList){
            writerNames.add(qnaService.getWriterName(qnaDto.getUserId()));
        }

        mv.setViewName("board/qna");
        mv.addObject("resultList", resultList);
        mv.addObject("writerName" , writerNames);
        return mv;
    }
    @RequestMapping("/write")
    public ModelAndView qnaWrite(ModelAndView mv, HttpServletRequest request) throws GeneralSecurityException, UnsupportedEncodingException {
        if(request.getSession().getAttribute("loginMember")==null){
            mv.addObject("warningMessage","로그인이 필요한 서비스입니다.");
            mv.setViewName("member/login");
            return mv;
        }
        Member m = (Member) request.getSession().getAttribute("loginMember");
        String memberName = aes256Util.decrypt(m.getMemName());
        int userId = m.getUserId();
        mv.addObject("userId",userId);
        mv.addObject("writerName",memberName);
        mv.setViewName("board/qnaWrite");
        return mv; }

    @PostMapping("/write")
    public ModelAndView insertQna(ModelAndView mv,
                                  QnaDto qnaDto,
                                  HttpServletRequest request){

        if(request.getSession().getAttribute("loginMember")==null){
            mv.addObject("warningMessage","로그인이 필요한 서비스입니다.");
            mv.setViewName("member/login");
            return mv;
        }
        // 세션 가지고 오기
        Member m = (Member) request.getSession().getAttribute("loginMember");
        log.info(qnaDto);
        log.info(m);

        String pwd = qnaDto.getQnaPwd();
        try {
             pwd = aes256Util.encrypt(pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("Well Encrypt > " + pwd);
        qnaDto.setQnaPwd(pwd);
        qnaDto.setUserId(m.getUserId());
        qnaService.createQna(qnaDto);

        mv.setViewName("redirect:/qna/list");
        return mv;
    }


    //비밀글일경우 거치는 페이지
    @RequestMapping("/secret")
    public ModelAndView qnaSecret(ModelAndView mv){
        mv.setViewName("board/qnaSecret");
        return mv; }

    @RequestMapping("/view/{qnaNo}")
    public ModelAndView qnaDetail(ModelAndView mv, @PathVariable("qnaNo") int qnaNo){
        log.info(qnaNo + "no <<<<<< BoardDetail - Load");

        mv.setViewName("board/qnaDetail");
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
