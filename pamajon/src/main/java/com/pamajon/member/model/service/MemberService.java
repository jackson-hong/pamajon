package com.pamajon.member.model.service;

import com.pamajon.member.model.vo.Member;

import java.util.Map;

public interface MemberService {
    int memberInsert(Member member);
    int selectOne(Map userId);
}
