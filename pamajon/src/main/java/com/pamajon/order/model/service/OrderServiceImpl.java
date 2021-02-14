package com.pamajon.order.model.service;

import com.pamajon.mapper.PurchaseMapper;
import com.pamajon.order.model.vo.*;
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

    @Override
    public int getAddressId() {
        return purchaseMapper.getAddressId();
    }

    @Override
    public int createAddress(AddressDto address) {
        return purchaseMapper.createAddress(address);
    }

    @Override
    public int createOrder(OrderDto orderDto) {
        return purchaseMapper.createOrder(orderDto);
    }

    @Override
    public int insertMileage(MileageDto mileageDto) {
        return purchaseMapper.insertMileage(mileageDto);
    }

    @Override
    public int insertSold(SoldDto sold) {
        return purchaseMapper.insertSold(sold);
    }

    @Override
    public int modifyOptionStock(SoldDto sold) {
        return purchaseMapper.modifyOptionStock(sold);
    }

    @Override
    public int stackMileage(MileageDto mileageDto) {
        return purchaseMapper.stackMileage(mileageDto);
    }

}
