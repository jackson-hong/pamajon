package com.pamajon.pamajonUserBatchProject.schdeuling.controller;

import com.pamajon.pamajonUserBatchProject.schdeuling.model.service.PamajonBatchSchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@Controller
public class PamajonBatchScheduling {

    private static final Logger LOGGER = LoggerFactory.getLogger(PamajonBatchScheduling.class);

    private PamajonBatchSchedulingService batchService;

    public PamajonBatchScheduling(PamajonBatchSchedulingService batchService){
        this.batchService=batchService;
    }

    // 유저 정보 수정 트리거 메소드
    public void batchA(){

        HashMap<String,String> batchAStatusMetaData = new HashMap<>();
        batchAStatusMetaData.put("batchClassName","PamajonBatchScheduling");
        batchAStatusMetaData.put("batchMethodName","batchA");

            LOGGER.info("");
            LOGGER.info("");
            LOGGER.info("");
            LOGGER.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            LOGGER.info("BatchA 시작");

            //배치 A 시작
            callBatch();

            LOGGER.info("BatchA 종료");
            LOGGER.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            LOGGER.info("");
            LOGGER.info("");
            LOGGER.info("");

    }
    // batch 메소드에 대한 외부 직접접근 권한 없어야함.
    private void callBatch(){

            LOGGER.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            LOGGER.info("휴면 계정 전환처리 배치 시작....");
            LOGGER.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");

            batchService.modifyUserStatus();

            LOGGER.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            LOGGER.info("휴면 계정 전환처리 배치 종료....");
            LOGGER.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");

    }

}
