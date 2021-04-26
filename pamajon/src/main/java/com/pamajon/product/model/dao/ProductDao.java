package com.pamajon.product.model.dao;

import org.apache.ibatis.session.SqlSession;

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
}
