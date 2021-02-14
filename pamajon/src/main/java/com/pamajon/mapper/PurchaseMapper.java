package com.pamajon.mapper;

import com.pamajon.order.model.vo.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {

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
