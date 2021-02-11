package com.pamajon.mapper;

import com.pamajon.order.model.vo.Member;
import com.pamajon.order.model.vo.MileageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {

    public Member getMember(int memNo);

    public MileageDto getMileage(int memNo);
}
