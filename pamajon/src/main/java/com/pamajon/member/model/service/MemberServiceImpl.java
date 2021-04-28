package com.pamajon.member.model.service;

import com.pamajon.member.model.dao.MemberDao;
import com.pamajon.member.model.vo.Member;
import com.pamajon.member.model.vo.MemberAddr;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberDao dao;
    private final SqlSession session;

    @Autowired
    public MemberServiceImpl(MemberDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public int memberInsert(Map mapForInsert) {
        return dao.memberInsert(session,mapForInsert);
    }

    @Override
    public int memberEmailInsert(Map emailMap) { return dao.memberEmailInsert(session, emailMap); }

    @Override
    public int idCheck(String email) {
        return dao.idCheck(session,email);
    }

    @Override
    public Map selectOneByMemId(Map map) {
        return dao.selectOneByMemId(session, map);
    }

    @Override
    public Member selectMemByUsid(int usid) {
        return dao.selectMemByUsid(session, usid);
    }

    @Override
    public Integer kakaoSelectUsidByEmailName(Map map) {
        return dao.kakaoSelectUsidByEmailName(session, map);
    }

    @Override
    public int countMembersByNamePhone(Map map) {
        return dao.countMembersByNamePhone(session, map);
    }

    @Override
    public int memberSelectByNamePhone(Map map) throws TooManyResultsException { return dao.memberSelectByNamePhone(session, map);}

    @Override
    public int addrInsert(MemberAddr addr) {
        return dao.addrInsert(session, addr);
    }

    @Override
    public int countAddr(int usid) {
        return dao.countAddr(session,usid);
    }

    @Override
    public List<MemberAddr> selectAddrList(int usid) {
        return dao.selectAddrList(session,usid);
    }

    @Override
    public MemberAddr selectAddr(int addrId) {
        return dao.selectAddr(session,addrId);
    }

    @Override
    public int addrUpdate(MemberAddr addr) {
        return dao.addrUpdate(session, addr);
    }

    @Override
    public int addrDelete(String addrId) {
        return dao.addrDelete(session, addrId);
    }

    @Override
    public int updatePasswd(Map map){return dao.updatePasswd(session, map);}

    @Override
    public int mileageInsert(Map map) {
        return dao.mileageInsert(session, map);
    }

    @Override
    public List mileageSelect(int usid) {
        return dao.mileageSelect(session, usid);
    }

    @Override
    public List<Integer> wishlist(int usid) {
        return dao.wishlist(session, usid);
    }

    @Override
    public List<Map> selectProductsForWish(List<Integer> wishList) {
        return dao.selectProductsForWish(session, wishList);
    }

    @Override
    public int wishDelete(Map map) {
        return dao.wishDelete(session, map);
    }

}
