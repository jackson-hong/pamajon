package com.pamajon.member.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pamajon.common.security.AES256Util;
import com.pamajon.member.model.service.MemberService;
import com.pamajon.member.model.service.MemberServiceImpl;
import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import com.pamajon.member.model.vo.Test;
import com.pamajon.order.model.vo.AddressDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Primary;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping(value = "/kakao")
    @ResponseBody
    public String kakao(@RequestBody Map input, ModelAndView mv, HttpServletRequest request) throws Exception{
        log.info(input);

        HttpSession sess = request.getSession();

        String email = (String)input.get("email");
        String name = (String)input.get("name");

        email = aes.encrypt(email);
        name = aes.encrypt(name);

        Map map = new HashMap();
        map.put("email", email);

        //가입된 회원인지 확인
        Integer usid = service.kakaoSelectUsidByEmailName(map);

        //가입되지 않은 경우
        if(usid == null || usid == 0){

            return "NONE";
        }

        //가입된 경우
        Member loginMember = service.selectMemByUsid(usid);
        sess.setAttribute("loginMember", loginMember);
        mv.setViewName("/member/myPage");
        return "EXIST";
    }

    //TEST용 맵핑
    @GetMapping("/kakao/join")
    public ModelAndView kakaoJoin(ModelAndView mv){
        mv.setViewName("/member/kakaoJoin");
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
    public Map idCheck(@RequestParam String email) throws GeneralSecurityException, UnsupportedEncodingException {
        log.info(email);
        email = aes.encrypt(email);
        int result = service.idCheck(email);
        Map jackson = new HashMap();
        jackson.put("result",result);
        return jackson;
    }

    @GetMapping("/join")
    public ModelAndView join(ModelAndView mv){
        mv.setViewName("/member/join");
        return mv;
    }

    @PostMapping("/")
    public ModelAndView joinEnd(ModelAndView mv, Model model, @RequestParam Map input, HttpSession sess) {

        String email = (String)input.get("email");
        String passwd = (String) input.get("memPwd");
        String name = (String) input.get("memName");
        String phone = (String) input.get("memPhone");

        try {
            email = aes.encrypt(email);
            passwd = passwordEncoder.encode(passwd);
            name = aes.encrypt(name);
            phone = aes.encrypt(phone);
        } catch (GeneralSecurityException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        //잘못된 input 처리
        if(email.isEmpty()||passwd.isEmpty()||name.isEmpty()||phone.isEmpty()){
            mv.addObject("msg","잘못된 정보가 존재합니다.");
            mv.addObject("loc","/member/login");
            mv.setViewName("/common/msg");
            return mv;
        }

        //DB에 Member Insert
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("phone", phone);
        int result = service.memberInsert(map);

        //잘못된 input으로 인한 DB 오류처리
        if(result == 0){
            mv.addObject("msg","잘못된 정보가 존재합니다.");
            mv.addObject("loc","/member/login");
            mv.setViewName("/common/msg");
        }

        //Usid 가져오기
        int usid = service.memberSelectByNamePhone(map);

        Map emailMap = new HashMap();
        emailMap.put("usid", usid);
        emailMap.put("email", email);
        emailMap.put("passwd", passwd);

        //Usid 와 email, passwd을 Member_id 테이블에 저장하기
        int emailResult = service.memberEmailInsert(emailMap);

        //잘못된 input 처리
        if(emailResult == 0){
            mv.addObject("msg","잘못된 정보가 존재합니다.");
            mv.addObject("loc","/member/login");
            mv.setViewName("/common/msg");
        }

        //세션 생성을 위한 Member 객체 생성
        Member loginMember = service.selectMemByUsid(usid);
        loginMember.setMemEmail(email);
        loginMember.setMemPhone(phone);

        //세션 생성
        sess.setAttribute("loginMember", loginMember);

        //마이페이지로 이동
        mv.setViewName("redirect:/member/myPage");

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
