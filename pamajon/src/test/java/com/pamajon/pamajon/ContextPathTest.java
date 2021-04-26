package com.pamajon.pamajon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContextPathTest {

    @Autowired
    HttpSession session;

    @Test
    public void contextTest(){


        System.out.println(System.getProperty("user.dir"));


    }
}
