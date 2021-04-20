package com.pamajon.member.model.dao;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
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
    public Map selectOneByMemId(SqlSession session, Map map) {
        return session.selectOne("member.selectOneByMemId", map);
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
    public int memberSelectByNamePhone(SqlSession session, Map map) throws TooManyResultsException {
        return session.selectOne("member.selectOneByNamePhone", map);
    }

    @Override
    public int countMembersByNamePhone(SqlSession session, Map map) {
        return session.selectOne("member.countMembersByNamePhone", map);
    }

    @Override
    public int addrInsert(SqlSession session, MemberAddr addr) {
        return session.insert("member.insertAddr", addr);
    }

    @Override
    public int countAddr(SqlSession session, int addrId) {
        return session.selectOne("member.countAddr", addrId);
    }

    @Override
    public int addrUpdate(SqlSession session, MemberAddr addr) {
        return session.update("member.addrUpdate", addr);
    }

    @Override
    public int addrDelete(SqlSession session, String addrId) {
        return session.delete("member.addrDelete", addrId);
    }

    @Override
    public MemberAddr selectAddr(SqlSession session, int addrId) {
        return session.selectOne("member.selectAddr", addrId);
    }

    @Override
    public List<MemberAddr> selectAddrList(SqlSession session, int usid) {
        return session.selectList("member.selectAddrList", usid);
    }

    @Override
    public int updatePasswd(SqlSession session, Map map) {
        return session.update("member.updatePasswd", map);
    }

    @Override
    public int mileageInsert(SqlSession session, Map map) {
        return session.insert("member.mileageInsert", map);
    }

    @Override
    public List mileageSelect(SqlSession session, int usid) {
        return session.selectList("member.mileageSelect", usid);
    }
}
