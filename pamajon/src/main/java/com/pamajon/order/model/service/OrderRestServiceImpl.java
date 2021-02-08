package com.pamajon.order.model.service;

import com.pamajon.mapper.OrderMapper;

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
    private OrderMapper orderMapper;

    @Override
    public List<AddressDto> getAddrList(int userNo) {
        return (ArrayList)orderMapper.getAddrList(userNo);
    }

    @Override
    public int regularCheck(int userNo) {
        return orderMapper.regularCheck(userNo);
    }

    @Override
    public int createAddress(AddressDto address) {
        return orderMapper.createAddress(address);
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
        return orderMapper.getAddress(addrNo);
    }
}
