package com.pamajon.admin.model.vo;

import com.pamajon.common.security.AES256Util;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;


@NoArgsConstructor
public class ShipmentListDto {

    private String orderId;
    private String orderEmail;
    private String receiver;
    private String orderPhone;
    private String productName;
    private String orderPurchase;
    private String orderDeliveryStatus;
    private String orderDate;
    @Autowired
    private AES256Util aes256Util;

    {
        try {
            aes256Util = new AES256Util();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public ShipmentListDto(String orderId, String orderEmail, String receiver, String orderPhone, String productName, String orderPurchase, String orderDeliveryStatus, String orderDate) throws UnsupportedEncodingException {
        this.orderId = orderId;
        this.orderEmail = orderEmail;
        this.receiver = receiver;
        this.orderPhone = orderPhone;
        this.productName = productName;
        this.orderPurchase = orderPurchase;
        this.orderDeliveryStatus = orderDeliveryStatus;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderEmail() throws GeneralSecurityException, UnsupportedEncodingException {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
    }

    public String getReceiver() throws GeneralSecurityException, UnsupportedEncodingException {
        return aes256Util.decrypt(receiver);
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getOrderPhone() throws GeneralSecurityException, UnsupportedEncodingException {
        return aes256Util.decrypt(orderPhone);
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderPurchase() {
        return orderPurchase;
    }

    public void setOrderPurchase(String orderPurchase) {
        this.orderPurchase = orderPurchase;
    }

    public String getOrderDeliveryStatus() {
        return orderDeliveryStatus;
    }

    public void setOrderDeliveryStatus(String orderDeliveryStatus) {
        this.orderDeliveryStatus = orderDeliveryStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return "ShipmentListDto{" +
                "orderId='" + orderId + '\'' +
                ", orderEmail='" + orderEmail + '\'' +
                ", receiver='" + aes256Util.decrypt(receiver) + '\'' +
                ", orderPhone='" + aes256Util.decrypt(orderPhone) + '\'' +
                ", productName='" + productName + '\'' +
                ", orderPurchase='" + orderPurchase + '\'' +
                ", orderDeliveryStatus='" + orderDeliveryStatus + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
