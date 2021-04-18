package com.pamajon.pamajon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EncodingTestClass {

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Test
    public void encodingTest(){
        System.out.println(passwordEncoder.encode("admin12345"));

        System.out.println(passwordEncoder.matches("admin12345",passwordEncoder.encode("admin12345")));

    }
}
