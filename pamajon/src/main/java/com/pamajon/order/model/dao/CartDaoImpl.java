package com.pamajon.order.model.dao;

import com.pamajon.order.model.vo.CartDto;
import com.pamajon.order.model.vo.CartListDto;
import com.pamajon.order.model.vo.ProductOptionDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao{

    @Override
    public void cartInsert(SqlSession session, CartDto cartDto) {
        session.insert("cart.cartInsert", cartDto);
    }

    @Override
    public List<CartListDto> cartList(SqlSession session, int userid) {
        return session.selectList("cart.cartList", userid);
    }

}
