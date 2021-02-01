package com.pamajon.member.model.service;

import com.pamajon.member.model.dao.MemberDao;
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
    public int memberInsert(Map inputs) {
        return dao.memberInsert(session,inputs);
    }
}
