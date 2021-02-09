package com.pamajon.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public interface MemberDao {
    int memberInsert(SqlSession session, Map inputs);
    int selectOne(SqlSession session, Map userId);
}
