package com.pamajon.member.model.dao;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public interface MemberDao {
    int memberInsert(SqlSession session, Map mapForInsert);
    int memberEmailInsert(SqlSession session, Map emailMap);
    int idCheck(SqlSession session, String email);
    Map selectOneByMemId(SqlSession session, Map map);
    Member selectMemByUsid(SqlSession session, int usid);
    Integer kakaoSelectUsidByEmailName(SqlSession session, Map map);
    int memberSelectByNamePhone(SqlSession session, Map map);
    int countMembersByNamePhone(SqlSession session, Map map);
    int addrInsert(SqlSession session, MemberAddr addr);
    int updatePasswd(SqlSession session, Map map);
}
