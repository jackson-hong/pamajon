package com.pamajon.order.model.vo;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderDto {

    private int orderId;
    private String userId;
    private String addrId;
    private String orderAddr;
    private String orderStatus;
    private String orderPhone;
    private String orderEmail;
    private String orderMessage;
    private String orderPurchase;
    private String orderDeliveryStatus;
    private String orderDate;
    private String orderCardNum;
    private String orderCardMon;
    private String orderCardYeadr;
    private String orderCardPass;
    private String orderMemBirth;
    private String orderTransName;
    private String orderMethod;
    private String orderAccountNum;
    private String orderKey;
    private String orderShipfee;
    private List<OrderDto> orderDtoList;

}
