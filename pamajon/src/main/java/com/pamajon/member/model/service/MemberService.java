package com.pamajon.member.model.service;

import java.util.Map;

public interface MemberService {
    int memberInsert(Map inputs);
    int selectOne(Map userId);
}
