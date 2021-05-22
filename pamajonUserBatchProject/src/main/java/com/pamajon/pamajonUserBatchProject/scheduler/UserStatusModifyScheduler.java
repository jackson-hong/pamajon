package com.pamajon.pamajonUserBatchProject.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Scheduled(cron = "${userStatusModifierABatchCronTab}")
    public void testSchedule(){

        LOGGER.info("??");

    }
}
