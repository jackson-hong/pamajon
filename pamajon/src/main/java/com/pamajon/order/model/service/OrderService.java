package com.pamajon.order.model.service;

import com.pamajon.order.model.vo.*;

public interface OrderService {

    public Member getMember(int memNo);

    public MileageDto getMileage(int memNo);

    public int getAddressId();

    public int createAddress(AddressDto address);

    public int createOrder(OrderDto orderDto);

    public int insertMileage(MileageDto mileageDto);

    public int insertSold(SoldDto sold);

    public int modifyOptionStock(SoldDto sold);

    public int stackMileage(MileageDto mileageDto);
}
