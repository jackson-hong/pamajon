package com.pamajon.admin.controller;

import com.pamajon.admin.model.service.AdminService;
import com.pamajon.admin.model.vo.AdminUser;
import com.pamajon.admin.model.vo.SearchParameterDto;
import com.pamajon.admin.model.vo.ShipmentListDto;
import com.pamajon.common.page.Pagination;
import com.pamajon.common.vo.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value="/admin")
@Controller
public class AdminController {

    private final Logger LOGGER= LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(PasswordEncoder passwordEncoder,AdminService adminService){
        this.passwordEncoder=passwordEncoder;
        this.adminService=adminService;
    }

    @GetMapping("/mainPage")
    public String gotoAdmin(HttpServletRequest request){

        //로그인 안되어있으면 메인페이지로 토스
        if(request.getSession().getAttribute("adminUser") == null){
            return "redirect:/warning";
        }

        return"admin/adminMainPage";

    }
    @GetMapping("/bResgistration")
    public String gotoResgistration(){

        return "admin/profile";
    }

    @GetMapping("/memberManager")
    public String gotoMemberHandler(){

        return "admin/memberHandler";
    }

    @GetMapping("/productManager")
    public String gotoProductHandler(){

        return "admin/productHandler";
    }

    @GetMapping("/productInsert")
    public String gotoInsertProduct(){

        return "admin/insertProduct";
    }

    @GetMapping("/shipment")
    public String gotoShipmentPage(){

        return "admin/shipping";
    }

    @GetMapping("/signin")
    public String gotoLoginPage(HttpServletRequest request){

        //로그인 되어있는 유저는 바로 메인페이지로 이동
        if(request.getSession().getAttribute("adminUser") != null){
            return "redirect:mainPage";
        }

     return "admin/adminLogin/adminLogin";
    }
    @GetMapping("/login")
    public String redirectUser(HttpServletRequest request){

        //로그인 되어있는 유저는 바로 메인페이지로 이동
        if(request.getSession().getAttribute("adminUser") != null){
            return "redirect:mainPage";
        }

        return "redirect:/warning";
    }


    @PostMapping("/login")
    public String adminLogin(AdminUser adminUser , HttpSession session , Model md , HttpServletResponse response){

        LOGGER.info(adminUser.toString());
        //아이디로 조회 해옴.
        AdminUser selectUser = adminService.getUser(adminUser);
        //로그인에 앞서 기존 세션값을 삭제
        session.removeAttribute("adminUser");

        if(selectUser==null){
            session.setAttribute("alarmMsg","아이디를 잘못 입력하셨습니다");
            session.setMaxInactiveInterval(1);
            return "redirect:signin";
        }
        if(selectUser!=null && !passwordEncoder.matches(adminUser.getAdminLoginPwd(),selectUser.getAdminLoginPwd())){
            //Fail count +1
            if(adminService.getFailCount(adminUser)>=5){
                if(!selectUser.getAdminApprStatus().equals("BLK")){
                    adminService.blockAdminUserStatus(adminUser);
                }
                session.setAttribute("alarmMsg","입력 실패 횟수가 5회를 초과 하여 더이상 로그인 하실 수 없습니다. 파마존 개발팀 또는 담당 매니저에게 연락 해 주시기 바랍니다.");
                session.setMaxInactiveInterval(1);
                return "redirect:signin";
            }
            adminService.increaseFailCount(adminUser);
            //Fail count select
            if(adminService.getFailCount(adminUser)!=5) {
                session.setAttribute("alarmMsg", "비밀번호를 잘못 입력하셨습니다 총[5]회 입력이 가능하며 총["
                        + (5 - adminService.getFailCount(adminUser)) + "]회 남았습니다.");
            }
            if(adminService.getFailCount(adminUser)==5) {
                if(!selectUser.getAdminApprStatus().equals("BLK")){
                    adminService.blockAdminUserStatus(adminUser);
                }

                session.setAttribute("alarmMsg", "입력 실패 횟수가 5회를 초과 하여 더이상 로그인 하실 수 없습니다. 파마존 개발팀 또는 담당 매니저에게 연락 해 주시기 바랍니다.");
            }
            session.setMaxInactiveInterval(2);
            return "redirect:signin";
        }
        //승인 채킹
        if(selectUser!=null && passwordEncoder.matches(adminUser.getAdminLoginPwd(),selectUser.getAdminLoginPwd())){

            if(selectUser.getAdminApprStatus().equals("DEN")){
                session.setAttribute("alarmMsg","승인이 거절된 계정입니다. 담당 매니저에게 연락해주시기 바랍니다.");
                session.setMaxInactiveInterval(2);
                return "redirect:signin";
            }
            if(selectUser.getAdminApprStatus().equals("DEL")){
                session.setAttribute("alarmMsg","삭제된 계정입니다. 담당 매니저에게 연락해주시기 바랍니다.");
                session.setMaxInactiveInterval(2);
                return "redirect:signin";
            }
            if(selectUser.getAdminApprStatus().equals("BLK")){
                session.setAttribute("alarmMsg","사용이 정지된 계정입니다. 담당 매니저에게 연락해주시기 바랍니다.");
                session.setMaxInactiveInterval(2);
                return "redirect:signin";
            }

        }

        if(selectUser!=null && passwordEncoder.matches(adminUser.getAdminLoginPwd(),selectUser.getAdminLoginPwd()) && selectUser.getAdminApprStatus().equals("APP")){
            adminService.resetFailCount(adminUser);
            session.setAttribute("adminUser",selectUser);
            session.setMaxInactiveInterval(30*60);

            if(adminUser.getRememberPwd()!=null){

                //세션에 대한 정보를 디비에 저장.
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("userId",selectUser.getAdminLoginId());
                map.put("sessionId",session.getId());
                map.put("expiryDate",new Date(System.currentTimeMillis()+(1000*60*60*24*7)));

                adminService.saveSessionInfo(map);

                Cookie cookie = new Cookie("loginCookie",session.getId());

                cookie.setPath("signin");
                //쿠키설정완료
                cookie.setMaxAge(60*60*24*7);
                response.addCookie(cookie);

            }

            return "redirect:mainPage";
        }

        md.addAttribute("msg","잘못된 정보가 존재합니다.");
        md.addAttribute("loc","/admin/signin");

        return "common/msg";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        LOGGER.info(" "+(AdminUser)request.getSession().getAttribute("adminUser"));
        //sessionId 와 session 만료일 삭제
        adminService.expireSessionInfo((AdminUser)request.getSession().getAttribute("adminUser"));

        request.getSession().invalidate();
        Cookie cookie = new Cookie("loginCookie",null);
        cookie.setMaxAge(0);
        cookie.setPath("signin");
        response.addCookie(cookie);



        return "redirect:signin";
    }
    @GetMapping("/shipmentList/{pageNum}")
    public ResponseEntity<List<Object>> getShipmentList(@PathVariable Integer pageNum
                                                                ){
        //Test 완료
        PageInfo pageInfo = adminService.getPage(pageNum);
        //Test 완료 (return 값에 결제 리스트와 마지막 index에는 페이지 정보가 담겨있음.)
        return new ResponseEntity<>(adminService.getShipmentList(pageInfo), HttpStatus.OK);
    }

    @PostMapping("/shipmentList")
    public ResponseEntity<List<Object>> getShipmentListBySearch(@RequestParam String searchParameter){


        return new ResponseEntity<>(adminService.getShipmentListBySearch(searchParameter),HttpStatus.OK);

    }




}
