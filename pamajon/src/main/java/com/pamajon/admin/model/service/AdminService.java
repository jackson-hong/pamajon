package com.pamajon.admin.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pamajon.admin.model.vo.AdminUser;
import com.pamajon.admin.model.vo.MonthlyRateDto;
import com.pamajon.admin.model.vo.ShipmentDetailDto;
import com.pamajon.common.vo.PageInfo;
import com.pamajon.order.model.vo.OrderDto;

import java.util.List;
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

    PageInfo getPage(Integer pageNum);

    List<Object> getShipmentList(PageInfo pageInfo);

    List<Object> getShipmentListBySearch(String searchParameter) throws JsonProcessingException;

    List<ShipmentDetailDto> getOrderListDetail(int orderNo);

    List<MonthlyRateDto> getMonthlySalesRate();
}
