package com.pamajon.order.model.service;

import com.pamajon.member.model.vo.Member;
import com.pamajon.order.model.vo.CartDto;
import com.pamajon.order.model.vo.CartInsert;
import com.pamajon.order.model.vo.CartListDto;
import com.pamajon.order.model.vo.ProductOptionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CartService {
    void cartInsert(ArrayList<CartDto> optionList, Optional<Member> member);
    List<CartListDto> cartList(int userId);
}
