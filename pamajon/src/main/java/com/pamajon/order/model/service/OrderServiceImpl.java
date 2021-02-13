package com.pamajon.order.model.service;

import com.pamajon.mapper.PurchaseMapper;
import com.pamajon.order.model.vo.Member;
import com.pamajon.order.model.vo.MileageDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderServiceImpl")
@Primary
public class OrderServiceImpl implements OrderService{

    private final PurchaseMapper purchaseMapper;

    public OrderServiceImpl(PurchaseMapper purchaseMapper){
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public Member getMember(int memNo) {
        return purchaseMapper.getMember(memNo);
    }

    @Override
    public MileageDto getMileage(int memNo) {
        return purchaseMapper.getMileage(memNo);
    }
}
