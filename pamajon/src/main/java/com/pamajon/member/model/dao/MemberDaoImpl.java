package com.pamajon.member.model.dao;

import com.pamajon.member.model.vo.Member;
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
    public int selectOne(SqlSession session, Map userId) {
        return session.selectOne("member.selectOne",userId);
    }
}
