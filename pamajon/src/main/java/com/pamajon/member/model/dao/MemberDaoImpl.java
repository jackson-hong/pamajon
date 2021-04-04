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
    public int memberInsert(SqlSession session, Map mapForInsert) {
        return session.insert("member.memberInsert", mapForInsert);
    }

    @Override
    public int memberEmailInsert(SqlSession session, Map emailMap) {
        return session.insert("member.memberEmailInsert",emailMap);
    }

    @Override
    public int idCheck(SqlSession session, String email) {
        return session.selectOne("member.selectOneByEmail",email);
    }

    @Override
    public Member selectOneByMemId(SqlSession session, String memId) {
        return session.selectOne("member.selectOneByMemId", memId);
    }

    @Override
    public Member selectMemByUsid(SqlSession session, int usid) {
        return session.selectOne("member.selectMemByUsid", usid);
    }

    @Override
    public Integer kakaoSelectUsidByEmailName(SqlSession session, Map map) {
        return session.selectOne("member.kakaoSelectUsidByEmailName", map);
    }

    @Override
    public int memberSelectByNamePhone(SqlSession session, Map map){
        return session.selectOne("member.selectOneByNamePhone", map);
    }

    @Override
    public int addrInsert(SqlSession session, MemberAddr addr) {
        return session.insert("member.insertAddr", addr);
    }
}
