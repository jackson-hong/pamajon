package com.pamajon.admin.model.service;

import com.pamajon.admin.model.vo.AdminUser;
import com.pamajon.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper){
        this.adminMapper = adminMapper;
    }
    @Override
    public AdminUser getUser(AdminUser adminUser) {
        return adminMapper.getUser(adminUser);
    }

    @Override
    public void increaseFailCount(AdminUser adminUser) {
        adminMapper.increaseFailCount(adminUser);
    }

    @Override
    public int getFailCount(AdminUser adminUser) {

        return adminMapper.getFailCount(adminUser);
    }

    @Override
    public void blockAdminUserStatus(AdminUser adminUser) {
        adminMapper.blockAdminUserStatus(adminUser);
    }

    @Override
    public void resetFailCount(AdminUser adminUser) {
        adminMapper.resetFailCount(adminUser);
    }

    @Override
    public void saveSessionInfo(Map<String, Object> map) {
        adminMapper.saveSessionInfo(map);
    }
}
