package com.pamajon.member.model.service;

import com.pamajon.member.model.dao.MemberDao;
import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("memberServiceImpl")
@Primary
public class MemberServiceImpl implements MemberService{

    @Qualifier("memberDaoImpl")
    private final MemberDao dao;
    private final SqlSession session;

    public MemberServiceImpl(MemberDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public int memberInsert(Member member) {
        return dao.memberInsert(session,member);
    }

    @Override
    public int idCheck(String userId) {
        return dao.idCheck(session,userId);
    }

    @Override
    public Member selectOneByMemId(String memId) {
        return dao.selectOneByMemId(session, memId);
    }

    @Override
    public int addrInsert(MemberAddr addr) {
        return dao.addrInsert(session, addr);
    }
}
