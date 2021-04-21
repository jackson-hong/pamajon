package com.pamajon.board.model.service;

import com.pamajon.board.model.dao.ProductDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("boardServiceImpl")
@Primary
public class ProductServiceImpl implements ProductService {

    @Qualifier("boardDaoImpl")
    private final ProductDao dao;
    private final SqlSession session;

    public ProductServiceImpl(ProductDao dao, SqlSession session){
        this.dao = dao;
        this.session = session;
    }

    @Override
    public List<HashMap> homeBoard() {
        return dao.homeBoard(session);
    }
}
