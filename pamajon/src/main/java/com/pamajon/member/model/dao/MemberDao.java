package com.pamajon.member.model.dao;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public interface MemberDao {
    int memberInsert(SqlSession session, Member member);
    int idCheck(SqlSession session, String userId);
    Member selectOneByMemId(SqlSession session, String memId);
    int addrInsert(SqlSession session, MemberAddr addr);
}
