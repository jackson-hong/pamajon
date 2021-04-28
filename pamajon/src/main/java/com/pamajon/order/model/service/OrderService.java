package com.pamajon.order.model.service;

import com.pamajon.order.model.vo.*;

import java.util.List;

public interface OrderService {

    public Member getMember(int memNo);

    public MileageDto getMileage(int memNo);

    public int getAddressId();

    public int createAddress(AddressDto address);

    public int createOrder(OrderDto orderDto);

    public int insertMileage(MileageDto mileageDto);

    public int insertSold(SoldDto sold);

    public int modifyOptionStock(ProductOptionDto productOptionDto);

    public int stackMileage(MileageDto mileageDto);

    public List<ProductOptionDto> getProductOption(ProductOptionDto productOptionDto);

    String getUserEmail(int userId);
}
