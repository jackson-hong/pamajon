package com.pamajon.order.model.service;

import com.pamajon.common.security.AES256Util;
import com.pamajon.mapper.AddressMapper;

import com.pamajon.order.model.EncryptAddress;
import com.pamajon.order.model.vo.AddressDto;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("orderRestServiceImpl")
@Primary
public class OrderRestServiceImpl implements OrderRestService{

    @Autowired
    private AddressMapper addressMapper;

    private final EncryptAddress encryptAddress;

    public OrderRestServiceImpl(EncryptAddress encryptAddress){
        this.encryptAddress=encryptAddress;
    }

    @Override
    public List<AddressDto> getAddrList(int userNo) {
        return encryptAddress.decryption((ArrayList)addressMapper.getAddrList(userNo));
    }

    //유저가 기본배송지로 등록을 했는지 체킹하는 메소드
    @Override
    public int regularCheck(int userNo) {
        return addressMapper.regularCheck(userNo);
    }

    //주소 입력이 이뤄지는 코드
    @Override
    public int createAddress(AddressDto address)  {
        return addressMapper.createAddress(encryptAddress.encryption(address));
    }

    @Override
    public int deleteAddress(HashMap map) {
        return addressMapper.deleteAddress(map);
    }

    @Override
    public int updateAddress(AddressDto address) {
        return addressMapper.updateAddress(address);
    }
    @Override
    public AddressDto getAddress(int addrNo) {
        return encryptAddress.decryption(addressMapper.getAddress(addrNo));
    }
    @Override
    public int modifyAddress(AddressDto address) {
        return addressMapper.modifyAddress(encryptAddress.encryption(address));
    }

    @Override
    public AddressDto getRegAddress(int userNo) {
        return encryptAddress.decryption(addressMapper.getRegAddress(userNo));
    }

    @Override
    public int regularAddressCnt(String userNo) {
        return addressMapper.regularAddressCnt(userNo);
    }
}
