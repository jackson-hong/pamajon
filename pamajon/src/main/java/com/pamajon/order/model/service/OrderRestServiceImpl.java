package com.pamajon.order.model.service;

import com.pamajon.common.security.AES256Util;
import com.pamajon.mapper.OrderMapper;

import com.pamajon.order.model.EncryptAddress;
import com.pamajon.order.model.vo.AddressDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("orderRestServiceImpl")
@Primary
public class OrderRestServiceImpl implements OrderRestService{

    @Autowired
    private OrderMapper orderMapper;

    private final EncryptAddress encryptAddress;

    public OrderRestServiceImpl(EncryptAddress encryptAddress){
        this.encryptAddress=encryptAddress;
    }

    @Override
    public List<AddressDto> getAddrList(int userNo) {
        return encryptAddress.decryption((ArrayList)orderMapper.getAddrList(userNo));
    }

    //유저가 기본배송지로 등록을 했는지 체킹하는 메소드
    @Override
    public int regularCheck(int userNo) {
        return orderMapper.regularCheck(userNo);
    }

    //주소 입력이 이뤄지는 코드
    @Override
    public int createAddress(AddressDto address)  {

        return orderMapper.createAddress(encryptAddress.encryption(address));
    }

    @Override
    public int deleteAddress(HashMap map) {
        return orderMapper.deleteAddress(map);
    }

    @Override
    public int updateAddress(AddressDto address) {
        return orderMapper.updateAddress(address);
    }
    @Override
    public AddressDto getAddress(int addrNo) {
        return encryptAddress.decryption(orderMapper.getAddress(addrNo));
    }
    @Override
    public int modifyAddress(AddressDto address) {
        return orderMapper.modifyAddress(encryptAddress.encryption(address));
    }

    @Override
    public AddressDto getRegAddress(int userNo) {
        return encryptAddress.decryption(orderMapper.getRegAddress(userNo));
    }
}
