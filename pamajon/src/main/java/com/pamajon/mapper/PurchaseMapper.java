package com.pamajon.mapper;

import com.pamajon.order.model.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PurchaseMapper {

    public Member getMember(int memNo);

    public MileageDto getMileage(int memNo);

    public int getAddressId();

    public int createAddress(AddressDto address);

    public int createOrder(OrderDto orderDto);

    public int insertMileage(MileageDto mileageDto);

    public int insertSold(SoldDto sold);

    public int modifyOptionStock(ProductOptionDto productOptionDto);

    public int stackMileage(MileageDto mileageDto);

    public ProductOptionDto getProductOption(ProductOptionDto productOptionDto);

    String getUserEmail(int userId);

    void insertSold_v2(List<SoldDto> soldList);

    void modifyOptionStock_v2(List<ProductOptionDto> optionList);
}
