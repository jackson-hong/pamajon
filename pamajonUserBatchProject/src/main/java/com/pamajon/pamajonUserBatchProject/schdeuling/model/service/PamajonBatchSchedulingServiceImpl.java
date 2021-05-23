package com.pamajon.pamajonUserBatchProject.schdeuling.model.service;

import com.pamajon.pamajonUserBatchProject.schdeuling.model.dao.PamajonBatchSchedulingDao;
import com.pamajon.pamajonUserBatchProject.schdeuling.model.vo.Member;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class PamajonBatchSchedulingServiceImpl implements PamajonBatchSchedulingService {

    private final SqlSession session;

    private static final Logger LOGGER = LoggerFactory.getLogger(PamajonBatchSchedulingServiceImpl.class);

    private final PamajonBatchSchedulingDao batchDao;

    private final int intervalSize = 5000;

    public void modifyUserStatus() {

        boolean breakpoint = true;
        HashMap<String,Object> intervalHandler = new HashMap<>();
        setIntervalInit(intervalHandler);

        try
        {
            do {
                List<Member> inactiveTargetmemberslist = batchDao.modifyUserStatus(session,intervalHandler);
                //조회해온 자료가 더이상 존재하지 않을 경우 반복문 종료.
                if(inactiveTargetmemberslist.size()<1){
                    breakpoint = false;
                }
                //존재할 경우 반복문 계속 진행.
                if(inactiveTargetmemberslist.size()>=1){
                    batchDao.updateUserStatus(session,inactiveTargetmemberslist);
                    setInterval(intervalHandler);
                }

            } while(breakpoint);


        }catch (Exception e){
            LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            LOGGER.info("휴면 계정 전환처리 배치 에러 발생 .........................");
            LOGGER.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            HashMap<String,String> exceptionMap = new HashMap<>();
            exceptionMap.put("content",e.toString());
            exceptionMap.put("class","PamajonBatchSchedulingServiceImpl");
            exceptionMap.put("applicationName","PamajonBatchProject");
            batchDao.exceptionLogUpdate(session,exceptionMap);
        }

    }
    //초기 Interval 설정.
    private void setIntervalInit(HashMap<String,Object> param){
        param.put("start", 1);
        param.put("end", intervalSize);
    }
    //초기 1~5000 조회후 그 다음 Interval 세팅.
    private void setInterval(HashMap<String,Object> param){
        int endInterval = (int) param.get("end");

        int start = endInterval + 1;
        int end   = endInterval + intervalSize;

        param.put("start", start);
        param.put("end", end);


    }

}
