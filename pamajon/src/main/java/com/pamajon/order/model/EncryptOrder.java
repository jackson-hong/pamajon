package com.pamajon.order.model;

import com.pamajon.common.security.AES256Util;
import com.pamajon.order.model.vo.OrderDto;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Component
public class EncryptOrder {

    private final AES256Util aes256Util;

    public EncryptOrder(AES256Util aes256Util) {
        this.aes256Util = aes256Util;
    }

    public OrderDto encryptOrder(OrderDto orderDto){

        try {
            orderDto.setOrderAddr(aes256Util.encrypt(orderDto.getOrderAddr()));
            orderDto.setOrderPhone(aes256Util.encrypt(orderDto.getOrderPhone()));
            orderDto.setOrderCardNum(aes256Util.encrypt(orderDto.getOrderCardNum()));

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public OrderDto decryptOrder(OrderDto orderDto){

        try {
            orderDto.setOrderAddr(aes256Util.decrypt(orderDto.getOrderAddr()));
            orderDto.setOrderPhone(aes256Util.decrypt(orderDto.getOrderPhone()));
            orderDto.setOrderCardNum(aes256Util.decrypt(orderDto.getOrderCardNum()));

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
