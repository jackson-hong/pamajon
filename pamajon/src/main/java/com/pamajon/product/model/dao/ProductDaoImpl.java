package com.pamajon.product.model.dao;

import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductImageDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    @Override
    public List<HashMap> selectProductByBig(SqlSession session, int cateId) {
        return session.selectList("product.selectProductByBig", cateId);
    }

    @Override
    public String selectBigCateName(SqlSession session, int cateId) {
        return session.selectOne("product.selectBigCateName", cateId);
    }

    @Override
    public List<HashMap> selectProductBySmall(SqlSession session, int cateId) {
        return session.selectList("product.selectProductBySmall", cateId);
    }

    @Override
    public String selectSmallCateName(SqlSession session, int cateId) {
        return session.selectOne("product.selectSmallCateName", cateId);
    }

    @Override
    public List<HashMap<String,String>> brandList(SqlSession session) {
        return session.selectList("product.brandList");
    }

    @Override
    public String selectBrandName(SqlSession session, int cateId) {
        return session.selectOne("product.selectBrandName", cateId);
    }

    @Override
    public List<HashMap> selectBrand(SqlSession session, int cateId) {
        return session.selectList("product.selectBrand", cateId);
    }

    @Override
    public List<HashMap> newArrival(SqlSession session) {
        return session.selectList("product.newArrival");
    }

    @Override
    public List<HashMap> productSearch(SqlSession session, String key) {
        return session.selectList("product.productSearch", key);
    }

    @Override
    public List<ProductImageDto> getImage(SqlSession session, int productId) {
        return session.selectList("product.getImage",productId);
    }

    @Override
    public List<ProductOptionDto> getOption(SqlSession session, int productId) {
        return session.selectList("product.getOption",productId);
    }

    @Override
    public ProductImageDto getThumbImg(SqlSession session, int productId) {
        return session.selectOne("product.getThumbImg",productId);
    }

    @Override
    public ProductDto getProduct(SqlSession session, int productId) {
        return session.selectOne("product.getProduct",productId);
    }


}
