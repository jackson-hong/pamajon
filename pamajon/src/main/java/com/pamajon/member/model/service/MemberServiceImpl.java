package com.pamajon.member.model.service;

import com.pamajon.member.model.dao.MemberDao;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "memberService")
public class MemberServiceImpl implements MemberService{

    private final MemberDao dao;
    private final SqlSession session;

    @Autowired
    public MemberServiceImpl(MemberDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public int memberInsert(Map inputs) {
        return dao.memberInsert(session,inputs);
    }
}
