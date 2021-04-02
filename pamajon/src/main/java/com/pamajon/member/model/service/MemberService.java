package com.pamajon.member.model.service;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;

import java.util.Map;

public interface MemberService {
    int memberInsert(Member member);
    int idCheck(String email);
    Member selectOneByMemId(String memId);
    int addrInsert(MemberAddr addr);
}
