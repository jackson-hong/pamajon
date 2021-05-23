package com.pamajon.pamajonUserBatchProject.schdeuling.model.dao;

import com.pamajon.pamajonUserBatchProject.schdeuling.model.vo.Member;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface PamajonBatchSchedulingDao {
    List<Member> modifyUserStatus(SqlSession session, HashMap<String,Object> intervalHandler);
    void updateUserStatus(SqlSession session, List<Member> inactiveTargetmemberslist);
    void exceptionLogUpdate(SqlSession session, HashMap<String, String> exceptionMap);
}
