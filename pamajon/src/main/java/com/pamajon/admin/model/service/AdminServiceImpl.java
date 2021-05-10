package com.pamajon.admin.model.service;

import com.pamajon.admin.model.vo.AdminUser;
import com.pamajon.admin.model.vo.ShipmentListDto;
import com.pamajon.common.page.Pagination;
import com.pamajon.common.security.AES256Util;
import com.pamajon.common.vo.PageInfo;
import com.pamajon.mapper.AdminMapper;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminMapper adminMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    private AES256Util aes256Util;

    public AdminServiceImpl(AdminMapper adminMapper,AES256Util aes256Util){
        this.adminMapper = adminMapper;
        this.aes256Util = aes256Util;
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

    @Override
    public AdminUser checkUserWithSessionKey(String sessionId) {
        return adminMapper.getUserbyCookieId(sessionId);
    }

    @Override
    public void expireSessionInfo(AdminUser loginUser) {
        adminMapper.expireSessionInfo(loginUser);
    }

    @Override
    public PageInfo getPage(Integer pageNum) {

        //총 오더가 몇개인지 가져와야함.
        int listCount = adminMapper.getListCount();

        return Pagination.getPageInfo(listCount,pageNum,10,30);
    }

    @Override
    public List<Object> getShipmentList(PageInfo pageInfo) {

   //     LOGGER.info(pageInfo.toString());

        int offset = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit();

        RowBounds rowBounds = new RowBounds(offset,pageInfo.getBoardLimit());

        List<ShipmentListDto> shipmentListDtos = adminMapper.getShipmentList(rowBounds);

        List<Object> shipmentListDtosAndPage = new ArrayList<>(shipmentListDtos);
        shipmentListDtosAndPage.add(pageInfo);

        LOGGER.info(shipmentListDtosAndPage.toString());

        return shipmentListDtosAndPage;
    }

    @Override
    public List<Object> getShipmentListBySearch(String searchParameter) {

        LOGGER.info(searchParameter.toString());

        return null;
    }


}
