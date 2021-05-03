package com.pamajon.member.model.service;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import org.springframework.data.relational.core.sql.In;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MemberService {
    int memberInsert(Map mapForInsert);
    int memberEmailInsert(Map emailMap);
    int idCheck(String email);
    Map selectOneByMemId(Map map);
    Member selectMemByUsid(int usid);
    Integer kakaoSelectUsidByEmailName(Map map);
    int countMembersByNamePhone(Map map);
    int memberSelectByNamePhone(Map map);
    int addrInsert(MemberAddr addr);
    int countAddr(int usid);
    List<MemberAddr> selectAddrList(int usid);
    MemberAddr selectAddr(int addrId);
    int addrUpdate(MemberAddr addr);
    int addrDelete(String addrId);
    int updatePasswd(Map map);
    int mileageInsert(Map map);
    List mileageSelect(int usid);
    List<Map> wishlist(int usid);
    List<Map> selectProductsForWish(List<Integer> wishList);
    int wishDelete(Map map);
    List<Map> memberOrderList(Map map);
}
