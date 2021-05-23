package com.pamajon.member.model.dao;

import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.List;
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
    int countAddr(SqlSession session, int addrId);
    int addrUpdate(SqlSession session, MemberAddr addr);
    int addrDelete(SqlSession session, String addrId);
    MemberAddr selectAddr(SqlSession session, int addrId);
    List<MemberAddr> selectAddrList(SqlSession session, int usid);
    int updatePasswd(SqlSession session, Map map);
    int mileageInsert(SqlSession session, Map map);
    List mileageSelect(SqlSession session, int usid);
    List<Map> wishlist(SqlSession session, int usid);
    List<Map> selectProductsForWish(SqlSession session, List<Integer> wishList);
    int wishDelete(SqlSession session, Map map);
    List<Map> memberOrderList(SqlSession session, Map map);
    int updateCurrentLoginTime(SqlSession session, Member m);

    int updateLoiginCount(SqlSession session, Member m);
}
