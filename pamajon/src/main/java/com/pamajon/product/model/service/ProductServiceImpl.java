package com.pamajon.product.model.service;

import com.pamajon.product.model.dao.ProductDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("productServiceImpl")
@Primary
public class ProductServiceImpl implements ProductService {

    @Qualifier("productDaoImpl")
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

    @Override
    public int wishInsert(HashMap map) {
        return dao.wishInsert(session, map);
    }

    @Override
    public int wishDuplicate(HashMap map) {
        return dao.wishDuplicate(session, map);
    }

    @Override
    public List<HashMap> bigCateList() {
        return dao.bigCateList(session);
    }

    @Override
    public List<HashMap> smallCateList() {
        return dao.smallCateList(session);
    }

    @Override
    public List<HashMap> selectProductByBig(int cateId) {
        return dao.selectProductByBig(session, cateId);
    }

    @Override
    public String selectBigCateName(int cateId) {
        return dao.selectBigCateName(session, cateId);
    }

    @Override
    public List<HashMap> selectProductBySmall(int cateId) {
        return dao.selectProductBySmall(session, cateId);
    }

    @Override
    public String selectSmallCateName(int cateId) {
        return dao.selectSmallCateName(session, cateId);
    }
}
