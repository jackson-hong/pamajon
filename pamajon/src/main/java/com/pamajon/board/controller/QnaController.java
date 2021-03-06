package com.pamajon.board.controller;

import com.pamajon.board.model.service.QnaServiceImpl;
import com.pamajon.board.model.vo.BoardDto;
import com.pamajon.board.model.vo.QnaDto;
import com.pamajon.common.page.PageFactory;
import com.pamajon.common.security.AES256Util;
import com.pamajon.common.vo.PageInfo;
import com.pamajon.member.model.vo.Member;
import com.pamajon.order.model.vo.ProductDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
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
    @GetMapping("/list/{pageNum}")
    public ModelAndView qna(@PathVariable("pageNum") int pageNum,
                            ModelAndView mv,
                            HttpServletRequest request) throws GeneralSecurityException, UnsupportedEncodingException {

        List<QnaDto> resultList = qnaService.listQna();
        // 데이터 정제해서 표시할 객체 추가

        List<BoardDto> boardDtoList = new ArrayList<>();
        //필요한 값들을 담아줌
        for(QnaDto qnaDto :resultList){
            BoardDto boardDto = new BoardDto();

            if(qnaDto.getProductId() != 0){
                boardDto =qnaService.getProductInfo(qnaDto.getProductId());
            }
            boardDto.setQnaId(qnaDto.getQnaId());
            boardDto.setUserId(qnaDto.getUserId());
            boardDto.setMemName(aes256Util.decrypt(qnaService.getWriterName(qnaDto.getUserId())));
            boardDto.setQnaTitle(qnaDto.getQnaTitle());
            boardDto.setQnaContent(qnaDto.getQnaContent());
            boardDto.setQnaModifyDate(qnaDto.getQnaModifyDate());
            boardDto.setQnaPwd(qnaDto.getQnaPwd());
            boardDto.setQnaStatus(qnaDto.getQnaStatus());

            log.info("setEverything");
            log.info(boardDto);
            boardDtoList.add(boardDto);
        }

//Paging
        int length = resultList.size();
        boardDtoList =boardDtoList.subList((pageNum-1)*10, pageNum*10 > length ? length : pageNum*10);
        String pageBar = PageFactory.getPageBar(length, pageNum ,10);

        mv.setViewName("board/qna");
        mv.addObject("pageBar", pageBar);
        mv.addObject("resultList", boardDtoList);
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
        mv.setViewName("redirect:/qna/list/1");
        return mv;
    }


//    //비밀글일경우 거치는 페이지
//    @RequestMapping("/secret/{qnaNo}")
//    public ModelAndView qnaSecret(ModelAndView mv, @PathVariable("qnaNo") int qnaNo){
//        mv.setViewName("board/qnaSecret");
//        return mv; }
//
//    @RequestMapping("/secret/view/{qnaNo}")
//    public ModelAndView qnaSecret(HttpServletRequest request, ModelAndView mv, @PathVariable("qnaNo") int qnaNo,@PathVariable("qnaPwd") String qnaPwd){
//
//        if(request.getSession().getAttribute("adminUser") != null){
//            mv.setViewName("board/qnaDetail");
//            return mv;
//        }
//        mv.setViewName("board/qnaSecret");
//        return mv;
//    }

    @GetMapping("/view/{qnaNo}")
    public ModelAndView qnaDetail(ModelAndView mv, @PathVariable("qnaNo") int qnaNo) throws GeneralSecurityException, UnsupportedEncodingException {
        log.info(qnaNo + "no <<<<<< BoardDetail - Load");
        QnaDto qnaDto = qnaService.readQna(qnaNo);
        log.info(qnaDto);
        BoardDto boardDto = new BoardDto();

        log.info(boardDto);
        // 상품정보가 있을 경우 상품 정보를 먼저 담는다.

        if(qnaDto.getProductId() != 0){
            boardDto = qnaService.getProductInfo(qnaDto.getProductId());
            boardDto.setProductId(qnaDto.getProductId());
        }

        boardDto.setQnaId(qnaDto.getQnaId());
        boardDto.setProductId(qnaDto.getProductId());
        boardDto.setUserId(qnaDto.getUserId());
        boardDto.setMemName(aes256Util.decrypt(qnaService.getWriterName(qnaDto.getUserId())));
        boardDto.setQnaTitle(qnaDto.getQnaTitle());
        boardDto.setQnaContent(qnaDto.getQnaContent());
        boardDto.setQnaModifyDate(qnaDto.getQnaModifyDate());
        boardDto.setQnaPwd(qnaDto.getQnaPwd());
        boardDto.setQnaStatus(qnaDto.getQnaStatus());

        mv.addObject("qna" , boardDto);
        mv.setViewName("board/qnaDetail");

        int total = qnaService.getTotal();
        int before = qnaNo -1;
        int after = qnaNo +1;

        if(after > total){
            after = -1;
        }

        if(before < 0){

        }

        ProductDto productInfo;
        /**
         * 넘겨줘서 처리해야하는 것들
         * 1.이전글, 이후글 제목과 링크
         * 2.연관된 상품이 있을 경우, 그 상품에 대한 정보, 가격, 링크
         * 3.같은 상품에 대한 질문들모음
         */


        return mv; }

    @GetMapping("/delete/{qnaNo}")
    public ModelAndView qnaDelete(ModelAndView mv, @PathVariable("qnaNo") int qnaNo){
        log.info("proceed Delete > Board No:" + qnaNo +"<");
        int result  = qnaService.deleteQna(qnaNo);

        if(result == 0){
            mv.setStatus(HttpStatus.BAD_REQUEST);
            return mv;
        }
        mv.addObject("message", qnaNo+"번 글을 삭제했습니다.");
        mv.setViewName("redirect:/qna/list/1");
        return mv;
    }

    @GetMapping("/edit/{qnaNo}")
    public ModelAndView qnaModify(ModelAndView mv, @PathVariable("qnaNo") int qnaNo, HttpServletRequest request) throws GeneralSecurityException, UnsupportedEncodingException {

        if(request.getSession().getAttribute("loginMember")==null){
            mv.addObject("warningMessage","로그인이 필요한 서비스입니다.");
            mv.setViewName("member/login");
            return mv;
        }
        // 세션 가지고 오기
        Member m = (Member) request.getSession().getAttribute("loginMember");
        log.info(m);


        log.info("proceed Modify > Board No:" + qnaNo +"<");
        QnaDto qnaDto = qnaService.readQna(qnaNo);
        log.info(qnaDto);
        BoardDto boardDto = new BoardDto();
        log.info(boardDto);
        // 상품정보가 있을 경우 상품 정보를 먼저 담는다.

        if(qnaDto.getProductId() != 0){
            boardDto = qnaService.getProductInfo(qnaDto.getProductId());
            boardDto.setProductId(qnaDto.getProductId());
        }

        boardDto.setQnaId(qnaDto.getQnaId());
        boardDto.setProductId(qnaDto.getProductId());
        boardDto.setUserId(qnaDto.getUserId());
        boardDto.setMemName(aes256Util.decrypt(qnaService.getWriterName(qnaDto.getUserId())));
        boardDto.setQnaTitle(qnaDto.getQnaTitle());
        boardDto.setQnaContent(qnaDto.getQnaContent());
        boardDto.setQnaModifyDate(qnaDto.getQnaModifyDate());
        boardDto.setQnaPwd(qnaDto.getQnaPwd());
        boardDto.setQnaStatus(qnaDto.getQnaStatus());

        mv.addObject("qna" , boardDto);
        mv.setViewName("board/qnaEdit");
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView updateQna(ModelAndView mv,
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
        qnaDto.setUserId(m.getUserId());
        qnaService.updateQna(qnaDto);
        mv.setViewName("redirect:/qna/view/" + qnaDto.getQnaId());
        return mv;
    }
}
