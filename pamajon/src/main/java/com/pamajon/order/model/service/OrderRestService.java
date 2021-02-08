package com.pamajon.order.model.service;

import com.pamajon.order.model.vo.AddressDto;

import java.util.HashMap;
import java.util.List;

public interface OrderRestService {

    public List<AddressDto> getAddrList(int userNo);

    public int regularCheck(int userNo);

    public int createAddress(AddressDto address);

    public int deleteAddress(HashMap map);

    public int updateAddress(AddressDto address);

    public AddressDto getAddress(int addrNo);
}
