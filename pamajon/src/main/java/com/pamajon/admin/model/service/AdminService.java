package com.pamajon.admin.model.service;

import com.pamajon.admin.model.vo.AdminUser;

import java.util.Map;

public interface AdminService {

    public AdminUser getUser(AdminUser adminUser);

    public void increaseFailCount(AdminUser adminUser);

    public int getFailCount(AdminUser adminUser);

    public void blockAdminUserStatus(AdminUser adminUser);

    void resetFailCount(AdminUser adminUser);

    void saveSessionInfo(Map<String, Object> map);

    public AdminUser checkUserWithSessionKey(String sessionId);


    void expireSessionInfo(AdminUser loginUser);
}
