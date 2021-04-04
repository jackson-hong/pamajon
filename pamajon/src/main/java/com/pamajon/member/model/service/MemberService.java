package com.pamajon.member.model.service;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;

import java.util.Map;

public interface MemberService {
    int memberInsert(Map mapForInsert);
    int memberEmailInsert(Map emailMap);
    int idCheck(String email);
    Member selectOneByMemId(String memId);
    Member selectMemByUsid(int usid);
    Integer kakaoSelectUsidByEmailName(Map map);
    int memberSelectByNamePhone(Map map);
    int addrInsert(MemberAddr addr);
}
