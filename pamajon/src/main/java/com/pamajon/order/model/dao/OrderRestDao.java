package com.pamajon.order.model.dao;

import com.pamajon.order.model.vo.AddressDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderRestDao")
@Primary
public class OrderRestDao implements OrderRestDaoMapper{

    @Override
    public List<AddressDto> getAddrList(int userNo) {
        return null;
    }
}
