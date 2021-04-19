package com.pamajon.pamajon;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
public class DateTestClass {

    @Test
    public void dateTest(){

        System.out.println(new Date(System.currentTimeMillis()+(1000*60*60*24*7)));


    }
}
