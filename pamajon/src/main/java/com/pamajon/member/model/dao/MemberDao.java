package com.pamajon.member.model.dao;

import com.pamajon.member.model.vo.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public interface MemberDao {
    int memberInsert(SqlSession session, Member member);
    int selectOne(SqlSession session, Map userId);
}
