package com.pamajon.order.controller;

import com.pamajon.common.gmail.GmailConfig;
import com.pamajon.order.model.service.OrderRestServiceImpl;
import com.pamajon.order.model.vo.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class OrderRestController {


    @Qualifier("orderRestServiceImpl")
    private final OrderRestServiceImpl orderRestService;
    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    @Qualifier("gmailConfig")
    GmailConfig gmailConfig;

    public OrderRestController(OrderRestServiceImpl orderRestService){
        this.orderRestService = orderRestService;
    }

    @GetMapping(value = "/order/addressinput" , produces="application/json; charset=utf-8")
    public ResponseEntity<List<AddressDto>> getAddressList(@RequestParam String userNo){

        return new ResponseEntity<>(orderRestService.getAddrList(Integer.parseInt(userNo)), HttpStatus.OK);
    }

    @PostMapping(value ="/order/address")
    public ResponseEntity<Integer> createAddress(AddressDto address) {

        //기존에 존재하던 주소를 일반주소로 바꿔야함.
        if(address.getRegularCheck().equals("1")){
            orderRestService.updateAddress(address);
        }
        return new ResponseEntity<>(orderRestService.createAddress(address),HttpStatus.OK);
    }
    @GetMapping("/order/regular")
    public ResponseEntity<Integer> regularCheck(@RequestParam  int userNo){

        return new ResponseEntity(orderRestService.regularCheck(userNo),HttpStatus.OK);
    }
    @DeleteMapping("/order/address")
    public ResponseEntity<Integer> deleteAddress(HttpServletRequest request , @RequestParam String userNo){
        HashMap<String,Object> map = new HashMap<>();

        map.put("addrList",request.getParameterValues("addrIdList[]"));
        map.put("userNo",userNo);
        return new ResponseEntity<>(orderRestService.deleteAddress(map),HttpStatus.OK);
    }

    @GetMapping("/order/address")
    public ResponseEntity<AddressDto> getAddress(@RequestParam String addrNo){

        return new ResponseEntity<>(orderRestService.getAddress(Integer.parseInt(addrNo)),HttpStatus.OK);
    }

    /**
     * @author YooPatrick
     * @param userNo
     * @return
     */
    @GetMapping("/order/regularAddress")
    public ResponseEntity<AddressDto> getRegularAddress(@RequestParam String userNo){
        logger.info("==========상품결제 페이지 입장시 기본 배송지 조회============");

        if(orderRestService.regularAddressCnt(userNo)>0){
            logger.info("==========기본 배송지가 등록 되어있음.============");
            return new ResponseEntity<>(orderRestService.getRegAddress(Integer.parseInt(userNo)),HttpStatus.OK);
        }
        logger.info("====================== 기본 배송지가 등록 되어있지 않은경우 null 값을 반환.");
        return new ResponseEntity<>(null,HttpStatus.OK);
    }


    @PutMapping("/order/address")
    public ResponseEntity<Integer> updateAddress(AddressDto address){
        //기본 배송지로 저장 하겠다 하고 선언했을시 기존에 있는 기본 배송지를 바꿔주어야함.
        if(address.getRegularCheck().equals("1")){
            orderRestService.updateAddress(address);
        }
        return new ResponseEntity<>(orderRestService.modifyAddress(address),HttpStatus.OK);
    }

    @PostMapping("/order/email")
    public ResponseEntity<Integer> sendEmail(
             String to,
             String productName
    ) throws Exception {
        gmailConfig.gmailMailSender(to,productName);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }



}
