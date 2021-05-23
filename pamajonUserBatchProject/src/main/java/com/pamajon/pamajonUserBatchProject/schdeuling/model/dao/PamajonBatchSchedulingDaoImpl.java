package com.pamajon.pamajonUserBatchProject.schdeuling.model.dao;

import com.pamajon.pamajonUserBatchProject.schdeuling.model.vo.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PamajonBatchSchedulingDaoImpl implements PamajonBatchSchedulingDao {
    @Override
    public List<Member> modifyUserStatus(SqlSession session , HashMap<String,Object> intervalHandler) {
        return session.selectList("memberMapper.modifyUserStatus",intervalHandler);
    }
    @Override
    public void updateUserStatus(SqlSession session, List<Member> inactiveTargetmemberslist) {
        session.update("memberMapper.updateUserStatus",inactiveTargetmemberslist);
    }

    @Override
    public void exceptionLogUpdate(SqlSession session, HashMap<String, String> exceptionMap) {
        session.insert("memberMapper.exceptionLogUpdate",exceptionMap);
    }
}
