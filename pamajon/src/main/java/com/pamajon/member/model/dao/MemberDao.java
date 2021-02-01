package com.pamajon.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public interface MemberDao {
    public int memberInsert(SqlSession session, Map inputs);
}
