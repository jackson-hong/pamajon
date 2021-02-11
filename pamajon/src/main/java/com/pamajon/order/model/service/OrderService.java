package com.pamajon.order.model.service;

import com.pamajon.order.model.vo.AddressDto;
import com.pamajon.order.model.vo.Member;
import com.pamajon.order.model.vo.MileageDto;

import java.util.List;

public interface OrderService {

    public Member getMember(int memNo);

    public MileageDto getMileage(int memNo);
}
