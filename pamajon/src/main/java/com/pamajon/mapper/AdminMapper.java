package com.pamajon.mapper;

import com.pamajon.admin.model.vo.*;
import com.pamajon.common.vo.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    public AdminUser getUser(AdminUser adminUser);

    public void increaseFailCount(AdminUser adminUser);

    public int getFailCount(AdminUser adminUser);

    void blockAdminUserStatus(AdminUser adminUser);

    void resetFailCount(AdminUser adminUser);

    void saveSessionInfo(Map<String, Object> map);

    AdminUser getUserbyCookieId(String sessionKey);

    void expireSessionInfo(AdminUser loginUser);

    int getListCount();

    List<ShipmentListDto> getShipmentList(RowBounds rowBounds);

    int getShipmentListBySearchCount(SearchParameterDto searchParameterDto);

    List<Object> getShipmentListBySearch(SearchParameterDto searchParameterDto,RowBounds rowBounds);

    List<ShipmentDetailDto> getOrderListDetail(int orderNo);

    List<MonthlyRateDto> getMonthlySalesRate();
}
