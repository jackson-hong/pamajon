package com.pamajon.member.model.dao;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("memberDaoImpl")
@Primary
public class MemberDaoImpl implements MemberDao{
    @Override
    public int memberInsert(SqlSession session, Member member) {
        return session.insert("member.memberInsert", member);
    }

    @Override
    public int idCheck(SqlSession session, String userId) {
        return session.selectOne("member.selectOne",userId);
    }

    @Override
    public Member selectOneByMemId(SqlSession session, String memId) {
        return session.selectOne("member.selectOneByMemId", memId);
    }

    @Override
    public int addrInsert(SqlSession session, MemberAddr addr) {
        return session.insert("member.insertAddr", addr);
    }
}
