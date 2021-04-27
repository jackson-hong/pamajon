package com.pamajon.product.model.service;

import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductImageDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;
import com.pamajon.product.model.dao.ProductDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<HashMap<String,String>> brandList() {
        return dao.brandList(session);
    }

    @Override
    public String selectBrandName(int cateId) {
        return dao.selectBrandName(session,cateId);
    }

    @Override
    public List<HashMap> selectBrand(int cateId) {
        return dao.selectBrand(session,cateId);
    }

    @Override
    public List<HashMap> newArrival() {
        return dao.newArrival(session);
    }

    @Override
    public List<HashMap> productSearch(String key) {
        return dao.productSearch(session, key);
    }

    @Override
    public List<ProductImageDto> getImage(int productId) {
        return dao.getImage(session,productId);
    }

    @Override
    public List<ProductOptionDto> getOption(int productId) {
        return dao.getOption(session,productId);
    }

    @Override
    public ProductImageDto getThumbImg(int productId) {
        return dao.getThumbImg(session,productId);
    }

    @Override
    public ProductDto getProduct(int productId) {
        return dao.getProduct(session,productId);
    }


}
