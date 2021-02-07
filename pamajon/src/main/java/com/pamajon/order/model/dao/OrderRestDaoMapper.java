package com.pamajon.order.model.dao;

import com.pamajon.order.model.vo.AddressDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderRestDaoMapper {

    public List<AddressDto> getAddrList(int userNo);
}
