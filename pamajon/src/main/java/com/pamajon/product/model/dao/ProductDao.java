package com.pamajon.product.model.dao;

import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductImageDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ProductDao {
    List<HashMap> homeBoard(SqlSession session);
    int wishInsert(SqlSession session, HashMap map);
    int wishDuplicate(SqlSession session, HashMap map);
    List<HashMap> bigCateList(SqlSession session);
    List<HashMap> smallCateList(SqlSession session);
    List<HashMap> selectProductByBig(SqlSession session, int cateId);
    String selectBigCateName(SqlSession session, int cateId);
    List<HashMap> selectProductBySmall(SqlSession session, int cateId);
    String selectSmallCateName(SqlSession session, int cateId);
    List<HashMap<String,String>> brandList(SqlSession session);
    String selectBrandName(SqlSession session, int cateId);
    List<HashMap> selectBrand(SqlSession session, int cateId);
    List<HashMap> newArrival(SqlSession session);
    List<HashMap> productSearch(SqlSession session, String key);
    List<ProductImageDto> getImage(SqlSession session, int productId);
    List<ProductOptionDto> getOption(SqlSession session, int productId);
    ProductImageDto getThumbImg(SqlSession session, int productId);
    ProductDto getProduct(SqlSession session, int productId);
}
