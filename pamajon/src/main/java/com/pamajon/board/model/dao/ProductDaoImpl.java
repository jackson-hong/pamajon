package com.pamajon.board.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("boardDaoImpl")
@Primary
public class ProductDaoImpl implements ProductDao {
    @Override
    public List<HashMap> homeBoard(SqlSession session) {
        return session.selectList("product.homeBoard");
    }
}
