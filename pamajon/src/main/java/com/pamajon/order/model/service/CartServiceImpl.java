package com.pamajon.order.model.service;

import com.pamajon.member.model.dao.MemberDao;
import com.pamajon.member.model.vo.Member;
import com.pamajon.order.model.dao.CartDao;
import com.pamajon.order.model.vo.CartDto;
import com.pamajon.order.model.vo.CartInsert;
import com.pamajon.order.model.vo.CartListDto;
import com.pamajon.order.model.vo.ProductOptionDto;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
public class CartServiceImpl implements CartService{

    private final CartDao dao;
    private final SqlSession session;

    @Autowired
    public CartServiceImpl(CartDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public void cartInsert(ArrayList<CartDto> optionList, Optional<Member> member) {
        member.orElseThrow(IllegalArgumentException::new);
        log.info(optionList);
        optionList.forEach(cartDto -> {
            cartDto.setUserId(member.get().getUserId());
            dao.cartInsert(session, cartDto);
        });
    }

    @Override
    public List<CartListDto> cartList(int userId) {
        return dao.cartList(session, userId);
    }

    @Override
    public void cartModify(Map input) {
        dao.cartModify(session, input);
    }

    @Override
    public int cartDelete(Map input) {
        return dao.cartDelete(session, input);
    }
}
