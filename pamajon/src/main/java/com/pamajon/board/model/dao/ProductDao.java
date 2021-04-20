package com.pamajon.board.model.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.*;

public interface ProductDao {
    List<HashMap> homeBoard(SqlSession session);
}
