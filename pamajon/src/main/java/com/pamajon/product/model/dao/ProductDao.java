package com.pamajon.product.model.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public interface ProductDao {
    List<HashMap> homeBoard(SqlSession session);
    int wishInsert(SqlSession session, HashMap map);
    int wishDuplicate(SqlSession session, HashMap map);
}
