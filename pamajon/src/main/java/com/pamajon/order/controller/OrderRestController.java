package com.pamajon.order.controller;

import com.pamajon.order.model.service.OrderRestServiceImpl;
import com.pamajon.order.model.vo.AddressDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class OrderRestController {

    @Qualifier("orderRestServiceImpl")
    private final OrderRestServiceImpl orderRestService;
    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    public OrderRestController(OrderRestServiceImpl orderRestService){
        this.orderRestService = orderRestService;
    }

    @GetMapping(value = "/order/addressinput" , produces="application/json; charset=utf-8")
    public ResponseEntity<List<AddressDto>> getAddress(@RequestParam String userNo){
        return new ResponseEntity<>(orderRestService.getAddrList(Integer.parseInt(userNo)), HttpStatus.OK);
    }

    @PostMapping(value ="/order/address")
    public ResponseEntity<Integer> createAddress(AddressDto address){

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

}
