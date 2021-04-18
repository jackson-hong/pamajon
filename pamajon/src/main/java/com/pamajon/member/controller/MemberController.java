package com.pamajon.member.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pamajon.common.security.AES256Util;
import com.pamajon.member.model.service.MemberService;
import com.pamajon.member.model.service.MemberServiceImpl;
import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import com.pamajon.order.model.vo.AddressDto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
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
    public ModelAndView myPage(ModelAndView mv, HttpServletRequest req) throws GeneralSecurityException, UnsupportedEncodingException {
        HttpSession sess = req.getSession();
        Member m = (Member)sess.getAttribute("loginMember");
        String name = m.getMemName();
        name = aes.decrypt(name);
        mv.addObject("name", name);
        mv.setViewName("/member/myPage");
        return mv;
    }

    //kakaologin
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
        loginMember.setMemEmail(email);
        loginMember.setIsSocial(1);
        sess.setAttribute("loginMember", loginMember);
        return "EXIST";
    }

    @PostMapping(value = "/kakao/join", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public ModelAndView kakaoJoin(ModelAndView mv, @RequestParam Map input){
        log.info(input);
        mv.addObject("name",(String)input.get("name"));
        mv.addObject("email",(String)input.get("email"));
        mv.setViewName("/member/kakaoJoin");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mv, @RequestParam Map input, HttpServletRequest req){
        mv.setViewName("/member/login");
        log.info(req.getSession().getAttribute("loginMember"));
        return mv;
    }
    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView mv, HttpServletRequest req){
        HttpSession sess = req.getSession();
        sess.invalidate();
        mv.setViewName("redirect:/member/login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginEnd(ModelAndView mv, @RequestParam Map input, HttpServletRequest req){
        log.info(input);
        String email = (String)input.get("loginId");
        String passwd = (String)input.get("loginPw");

        if(email.isEmpty() || passwd.isEmpty()){
            return msg(mv, "잘못된 입력입니다.", "member/login");
        }

        //암호화된 값으로 변환
        try {
            email = aes.encrypt(email);
        } catch (GeneralSecurityException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        Map mapForService = new HashMap();
        mapForService.put("email",email);
        Map resultMap = new HashMap();
        try {
            resultMap = service.selectOneByMemId(mapForService);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        //아이디가 존재하지 않을 경우
        if(resultMap == null){
            return msg(mv, "존재하지 않는 아이디입니다.", "/member/login");
        }

        String passwdFromDB = (String)resultMap.get("passwd");

        if(!passwordEncoder.matches(passwd, passwdFromDB)){
            return msg(mv, "비밀번호를 확인해주세요","/member/login");
        }

        Integer usid;
        Integer isSocial;
        log.info(resultMap);
        usid = (Integer)resultMap.get("usid");
        isSocial = (Integer) resultMap.get("isSocial");

        //소셜로그인으로 가입된 아이디일 경우 리디렉
        if(isSocial == 1){
            return msg(mv, "카카오 ID로 접속해주세요", "/member/login");
        }

        //로그인 성공
        Member m = service.selectMemByUsid(usid);
        m.setMemPwd(passwdFromDB);
        m.setMemEmail(email);
        m.setIsSocial(isSocial);
        HttpSession sess = req.getSession();
        sess.setAttribute("loginMember",m);
        mv.setViewName("redirect:/member/myPage");
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
        log.info(input);
        boolean isSocial = Boolean.parseBoolean((String)input.get("isSocial"));
        String email = (String)input.get("email");
        String passwd = null;
        String name = (String) input.get("memName");
        String phone = (String) input.get("memPhone");
        //소셜로그인이 아닐 경우
        if(!isSocial) passwd = (String) input.get("memPwd");

        try {
            email = aes.encrypt(email);
            if(!isSocial){
                passwd = (String) input.get("memPwd");
                passwd = passwordEncoder.encode(passwd);
            }
            name = aes.encrypt(name);
            phone = aes.encrypt(phone);
        } catch (GeneralSecurityException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        //잘못된 input 처리
        if(email.isEmpty()||name.isEmpty()||phone.isEmpty()){
            mv.addObject("msg","잘못된 정보가 존재합니다.");
            mv.addObject("loc","/member/login");
            mv.setViewName("/common/msg");
            return mv;
        }

        //중복된 회원 확인
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("phone", phone);
        int findDuplicate = service.countMembersByNamePhone(map);
        if(findDuplicate > 0){
            mv.addObject("msg","이전에 가입된 아이디가 존재합니다");
            mv.addObject("loc","/member/join");
            mv.setViewName("/common/msg");
            return mv;
        }

        //DB에 Member Insert
        int result = service.memberInsert(map);

        //잘못된 input으로 인한 DB 오류처리
        if(result == 0){
            mv.addObject("msg","잘못된 정보가 존재합니다.");
            mv.addObject("loc","/member/login");
            mv.setViewName("/common/msg");
            return mv;
        }

        //Usid 가져오기
        Integer usid = 0;
        try {
            usid = service.memberSelectByNamePhone(map);
        }catch (TooManyResultsException e){
            //이미 가입한 회원 처리
            e.printStackTrace();
            mv.addObject("msg","이전에 가입된 아이디가 존재합니다");
            mv.addObject("loc","/member/join");
            mv.setViewName("/common/msg");
            return mv;
        }

        Map emailMap = new HashMap();
        emailMap.put("usid", usid);
        emailMap.put("email", email);
        emailMap.put("passwd", passwd);
        emailMap.put("isSocial",isSocial);

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
    public ModelAndView modify(ModelAndView mv, HttpSession sess) throws GeneralSecurityException, UnsupportedEncodingException {
        Member loginMember = (Member) sess.getAttribute("loginMember");
        log.info(loginMember);
        //소셜 아이디인지 확인
        String name = aes.decrypt(loginMember.getMemName());
        String email = aes.decrypt(loginMember.getMemEmail());
        mv.addObject("name", name);
        mv.addObject("email",email);
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

    private ModelAndView msg(ModelAndView mv, String msg, String loc){
        mv.addObject("msg",msg);
        mv.addObject("loc",loc);
        mv.setViewName("/common/msg");
        return mv;
    }
}
