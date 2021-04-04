package com.pamajon.order.controller;

import com.pamajon.order.JSONConvertor;
import com.pamajon.order.JSONToObject;
import com.pamajon.order.model.EncryptAddress;
import com.pamajon.order.model.EncryptOrder;
import com.pamajon.order.model.service.OrderService;
import com.pamajon.order.model.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Qualifier("orderServiceImpl")
    private final OrderService orderService;
    @Autowired
    private JSONConvertor jsonConvertor;
    @Autowired
    private JSONToObject jsonToObject;
    @Autowired
    private EncryptAddress encryptAddress;
    @Autowired
    private EncryptOrder encryptOrder;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/order/purchase")
    public String gotoPurchase(Model model, Member member, ProductOptionDto productOptionDto){
            Member m = orderService.getMember(1);
            model.addAttribute("productList",orderService.getProductOption(productOptionDto));
            model.addAttribute("member",m);
            model.addAttribute("mileage",orderService.getMileage(1));

            return "/order/orderform";
        }

    @RequestMapping("/order/addresslist")
    public String gotoAddress()
    {

        return "/order/addressInput";
    }
    @PostMapping("/order/purchase")
    @ResponseBody
    public ResponseEntity<Integer> updatePurchaseInfo(
            @RequestParam String orderDto,//2
            @RequestParam String soldDto,
            @RequestParam String addressDto,//1
            @RequestParam String usedMileageDto,
            @RequestParam String stackMileageDto){

        OrderDto order = jsonToObject.converToOrder(jsonConvertor.jsonConvertor(orderDto));
        SoldDto soldDtos = jsonToObject.convertToSoldList(jsonConvertor.jsonArrayConvertor(soldDto));
        AddressDto address = jsonToObject.convertToAddressDto(jsonConvertor.jsonConvertor(addressDto));
        MileageDto usedmileage =  jsonToObject.convertToMileage(jsonConvertor.jsonConvertor(usedMileageDto));
        MileageDto stackMileage = jsonToObject.convertToMileage(jsonConvertor.jsonConvertor(stackMileageDto));

        int addrResult = 0;
        int orderResult = 0;
        int mileageResult = 0;
        int soldResult = 0;
        int stackResult = 0;
        /*
        System.out.println(encryptOrder.encryptOrder(order));
        System.out.println(soldDtos);
        System.out.println(address);
        System.out.println(usedmileage);
        System.out.println(stackMileage);
        */
        //주소부터 insert.
        if(address.getAddrReloadCheck().equals("reloaded")){
          addrResult = orderService.createAddress(encryptAddress.encryption(address));
        }
        orderResult = orderService.createOrder(encryptOrder.encryptOrder(order));
        mileageResult = orderService.insertMileage(usedmileage);
        stackResult = orderService.stackMileage(stackMileage);

        //옵션별 배열 인서트
        for (int i = 0 ; i<soldDtos.getSoldList().size();i++){

            soldResult = orderService.insertSold(soldDtos.getSoldList().get(i));
            soldResult = orderService.modifyOptionStock(soldDtos.getSoldList().get(i));
            soldResult *= soldResult; //하나라도 insert 안되면 0이 리턴됨
        }
        /*
        //이녀석은 0일수도 있고 아닐수도있음.
        System.out.println(addrResult);
        System.out.println(orderResult);
        System.out.println(mileageResult);
        System.out.println(soldResult);
        System.out.println(stackResult);
        */
        return new ResponseEntity<>(orderResult*mileageResult*soldResult*stackResult,HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/order/addressId")
    public ResponseEntity<Integer> getAddressId(){
        return new ResponseEntity(orderService.getAddressId(), HttpStatus.OK);
    }
}
