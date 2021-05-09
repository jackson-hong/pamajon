package com.pamajon.pamajon;

import com.pamajon.admin.model.service.AdminServiceImpl;
import com.pamajon.common.vo.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminTest {

    @Autowired
    private AdminServiceImpl adminService;


    @Test
    public void adminPagingTest(){

        PageInfo pageInfo = adminService.getPage(1);
        //Test 완료
        List<Object> shipmentListDtos = adminService.getShipmentList(pageInfo);


    }
}
