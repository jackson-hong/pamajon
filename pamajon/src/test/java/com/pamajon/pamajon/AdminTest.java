package com.pamajon.pamajon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pamajon.admin.model.service.AdminServiceImpl;
import com.pamajon.common.security.AES256Util;
import com.pamajon.common.vo.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminTest {

    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    AES256Util aes256Util;

    @Test
    public void adminPagingTest() throws GeneralSecurityException, UnsupportedEncodingException, JsonProcessingException {

        System.out.println(adminService.getMonthlySalesRate());

    }

    @Test
    public void decryptoMachine(){


    }
}
