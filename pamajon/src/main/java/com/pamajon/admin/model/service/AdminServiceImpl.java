package com.pamajon.admin.model.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pamajon.admin.AscendingInteger;
import com.pamajon.admin.SetSearchParameterDtoFromJSON;
import com.pamajon.admin.model.vo.*;
import com.pamajon.common.page.Pagination;
import com.pamajon.common.security.AES256Util;
import com.pamajon.common.vo.PageInfo;
import com.pamajon.mapper.AdminMapper;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

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

        return Pagination.getPageInfo(listCount,pageNum,10,10);
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
    public List<Object> getShipmentListBySearch(String searchParameter) throws JsonProcessingException {
        //DEBUGGING JSON
        //{"pageNum":1,"searchOption":"ReceiverEmail","startDate":"2021-05-01","endDate":"2021-05-03","searchDateOptionCheckBox":"unchecked","searchOrderValue":"asd"}
        Map<String,String> searchParameterMap = new HashMap<>();

        //StringType JSON 객체를 Map 으로 치환
        searchParameterMap = new ObjectMapper().readValue(searchParameter,Map.class);

        //Map 으로 치환된 JSON 타입을 객체형으로 변환
        SearchParameterDto searchParameterDto = SetSearchParameterDtoFromJSON.converToObject(searchParameterMap);

        //페이징 객체 생성
        int listCount = adminMapper.getShipmentListBySearchCount(searchParameterDto);
        PageInfo pageInfo = Pagination.getPageInfo(listCount,searchParameterDto.getPageNum(),10,10);

        //몇개의 데이터를 갖고올 것인지 결정하여 RowBounds 에 담음
        int offset = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit();
        RowBounds rowBounds = new RowBounds(offset,pageInfo.getBoardLimit());

        List<Object> getShipmentListBySearch = adminMapper.getShipmentListBySearch(searchParameterDto,rowBounds);
        getShipmentListBySearch.add(pageInfo);

        return getShipmentListBySearch;
    }

    @Override
    public List<ShipmentDetailDto> getOrderListDetail(int orderNo) {

        return adminMapper.getOrderListDetail(orderNo);
    }

    @Override
    public List<MonthlyRateDto> getMonthlySalesRate() {

        List<MonthlyRateDto> monthlyRateDtos = adminMapper.getMonthlySalesRate();

        // 0월은 존재하지 않음. 가시성을 높이기 위하여 13까지 배열을 설정하며 0번 index 는 사용하지 않음.
        boolean [] monthCheck = new boolean[13];

        // 해당월에 해당하는 boolean 배열을 true 로 셋팅.
        for(int i = 0; i<monthlyRateDtos.size() ;i++){
            monthCheck[monthlyRateDtos.get(i).getMonth()] = true;
        }
        for(int i = 1 ; i<monthCheck.length; i++){

            if(monthCheck[i]==false){
                // 해당월 자료가 안넘어왔을시 0으로 셋팅
                monthlyRateDtos.add(new MonthlyRateDto(i,0));
            }
        }
        //1월부터 12월까지 정렬.
        Collections.sort(monthlyRateDtos,new AscendingInteger());

        return monthlyRateDtos;
    }


}
