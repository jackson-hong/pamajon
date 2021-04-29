package com.pamajon.order.model.dao;

import com.pamajon.order.model.vo.CartDto;
import com.pamajon.order.model.vo.CartListDto;
import com.pamajon.order.model.vo.ProductOptionDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public interface CartDao {
    void cartInsert(SqlSession session, CartDto cartDto);
    List<CartListDto> cartList(SqlSession session, int userId);
    void cartModify(SqlSession session, Map input);
    int cartDelete(SqlSession session, Map input);
}
