package com.pamajon.pamajonUserBatchProject.scheduler;

import com.pamajon.pamajonUserBatchProject.schdeuling.controller.PamajonBatchScheduling;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableScheduling
@Component
@PropertySource("classpath:context-scheduling.properties")
public class UserStatusModifyScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStatusModifyScheduler.class);

    private PamajonBatchScheduling pamajonBatchScheduling;

    @Autowired
    public UserStatusModifyScheduler(PamajonBatchScheduling pamajonBatchScheduling){
        this.pamajonBatchScheduling=pamajonBatchScheduling;
    }

    @Scheduled(cron = "${userStatusModifier_A_BatchTrigger}")
    public void testSchedule(){

        pamajonBatchScheduling.batchA();

    }
}
