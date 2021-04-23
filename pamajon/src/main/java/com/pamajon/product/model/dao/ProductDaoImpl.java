package com.pamajon.product.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("productDaoImpl")
@Primary
public class ProductDaoImpl implements ProductDao {
    @Override
    public List<HashMap> homeBoard(SqlSession session) {
        return session.selectList("product.homeBoard");
    }

    @Override
    public int wishInsert(SqlSession session, HashMap map) {
        return session.insert("product.wishInsert", map);
    }

    @Override
    public int wishDuplicate(SqlSession session, HashMap map) {
        return session.selectOne("product.wishDuplicate", map);
    }

    @Override
    public List<HashMap> bigCateList(SqlSession session) {
        return session.selectList("product.bigCateList");
    }

    @Override
    public List<HashMap> smallCateList(SqlSession session) {
        return session.selectList("product.smallCateList");
    }
}
