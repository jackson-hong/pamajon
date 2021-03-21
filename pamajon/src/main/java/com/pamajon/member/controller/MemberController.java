package com.pamajon.member.controller;

import com.pamajon.common.security.AES256Util;
import com.pamajon.member.model.service.MemberService;
import com.pamajon.member.model.service.MemberServiceImpl;
import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import com.pamajon.order.model.vo.AddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
@Log4j2
@RestController
@RequestMapping("/member")
@SessionAttributes("loginMember")
public class MemberController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AES256Util aes;

    @Qualifier("memberServiceImpl")
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/myPage")
    public ModelAndView myPage(ModelAndView mv, HttpServletRequest req){
        mv.setViewName("/member/myPage");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv, @RequestParam Map input){
        mv.setViewName("/member/login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginEnd(ModelAndView mv, @RequestParam Map input, HttpServletRequest req){
        Member m = service.selectOneByMemId((String)input.get("loginId"));
        if(m == null){
            log.info("로그인 실패");
            mv.addObject("msg","아이디나 비밀번호가 틀립니다.");
            mv.addObject("loc","/member/login");
            mv.setViewName("/common/msg");
        }else {
            HttpSession sess = req.getSession();
            sess.setAttribute("loginMember",m);
            mv.setViewName("redirect:/member/myPage");
        }

        return mv;
    }

    @GetMapping("/idCheck")
    public Map idCheck(@RequestParam String userId){
        int result = service.idCheck(userId);
        Map jackson = new HashMap();
        jackson.put("result",result);
        return jackson;
    }

    @GetMapping("/join")
    public ModelAndView join(ModelAndView mv){
        mv.setViewName("/member/join");
        return mv;
    }

    @PostMapping("/insert")
    public ModelAndView joinEnd(ModelAndView mv, Model model, @ModelAttribute Member member, @ModelAttribute MemberAddr addr, HttpSession sess) {
        //비밀번호 단방향 암호화
        member.setMemPwd(passwordEncoder.encode(member.getMemPwd()));
        try {
            //양방향 암호화
            member.setMemEmail(aes.encrypt(member.getMemEmail()));
            member.setMemPwdcheckA(aes.encrypt(member.getMemPwdcheckA()));
            member.setMemPhone(aes.encrypt(member.getMemPhone()));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //DB 저장
        service.memberInsert(member);
        //세션으로 쓸 멤버 객체 생성
        Member m = service.selectOneByMemId(member.getMemId());

        //사용자가 주소를 입력했을경우
        if(!addr.getAddrZipcode().isEmpty()&&!addr.getAddr().isEmpty()&&!addr.getAddrDetail().isEmpty()){
            addr.setAddrName(member.getMemName());
            addr.setAddrReceiver(member.getMemName());
            addr.setAddrPhone(member.getMemPhone());
            addr.setUserId(m.getUserId());
            //주소 입력
            service.addrInsert(addr);
        }

        sess.setAttribute("loginMember", m);
        mv.setViewName("member/myPage");
        return mv;
    }

    @RequestMapping("/orderList")
    public ModelAndView orderList(ModelAndView mv) {
        mv.setViewName("/member/orderList");
        return mv;
    }

    @RequestMapping("/modify")
    public ModelAndView modify(ModelAndView mv){
        mv.setViewName("/member/modify");
        return mv;
    }

    @RequestMapping("/wishList")
    public ModelAndView wishList(ModelAndView mv){
        mv.setViewName("/member/wishList");
        return mv;
    }

    @RequestMapping("/mileage")
    public ModelAndView mileage(ModelAndView mv){
        mv.setViewName("/member/mileage");
        return mv;
    }

    @RequestMapping("/address")
    public ModelAndView address(ModelAndView mv){
        mv.setViewName("/member/address");
        return mv;
    }
}
