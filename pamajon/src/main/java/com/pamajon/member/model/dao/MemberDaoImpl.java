package com.pamajon.member.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MemberDaoImpl implements MemberDao{
    @Override
    public int memberInsert(SqlSession session, Map inputs) {
        return session.insert("member.memberInsert", inputs);
    }
}
