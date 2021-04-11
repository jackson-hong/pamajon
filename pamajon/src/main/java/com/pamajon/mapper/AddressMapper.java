package com.pamajon.mapper;

import com.pamajon.order.model.vo.AddressDto;
import com.pamajon.order.model.vo.ProductOptionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AddressMapper {

    public List<AddressDto> getAddrList(int userNo);

    public int regularCheck(int userNo);

    public int createAddress(AddressDto address);

    public int updateAddress(AddressDto address);

    public int deleteAddress(HashMap map);

    public AddressDto getAddress(int addrNo);

    public int modifyAddress(AddressDto address);

    public AddressDto getRegAddress(int userNo);

    int regularAddressCnt(String userNo);
}
