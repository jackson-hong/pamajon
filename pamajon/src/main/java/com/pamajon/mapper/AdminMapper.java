package com.pamajon.mapper;

import com.pamajon.admin.model.vo.AdminUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AdminMapper {

    public AdminUser getUser(AdminUser adminUser);

    public void increaseFailCount(AdminUser adminUser);

    public int getFailCount(AdminUser adminUser);

    void blockAdminUserStatus(AdminUser adminUser);

    void resetFailCount(AdminUser adminUser);

    void saveSessionInfo(Map<String, Object> map);
}
