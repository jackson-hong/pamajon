package com.pamajon.order.model.service;

import com.pamajon.common.security.AES256Util;
import com.pamajon.mapper.PurchaseMapper;
import com.pamajon.order.JSONConvertor;
import com.pamajon.order.JSONToObject;
import com.pamajon.order.model.EncryptAddress;
import com.pamajon.order.model.EncryptOrder;
import com.pamajon.order.model.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("orderServiceImpl")
@Primary
public class OrderServiceImpl implements OrderService{

    @Autowired
    private final PurchaseMapper purchaseMapper;
    @Autowired
    private JSONConvertor jsonConvertor;
    @Autowired
    private JSONToObject jsonToObject;
    @Autowired
    private EncryptAddress encryptAddress;
    @Autowired
    private EncryptOrder encryptOrder;
    @Autowired
    private AES256Util aes256Util;
    @Autowired
    private EncryptMember encryptMember;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);


    public OrderServiceImpl(PurchaseMapper purchaseMapper){
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public Member getMember(int memNo) {
        return purchaseMapper.getMember(memNo);
    }

    @Override
    public MileageDto getMileage(int memNo) {
        return purchaseMapper.getMileage(memNo);
    }

    @Override
    public int getAddressId() {
        return purchaseMapper.getAddressId();
    }

    @Override
    public int createAddress(AddressDto address) {
        return purchaseMapper.createAddress(address);
    }

    @Override
    public int createOrder(OrderDto orderDto) {
        return purchaseMapper.createOrder(orderDto);
    }

    @Override
    public int insertMileage(MileageDto mileageDto) {
        return purchaseMapper.insertMileage(mileageDto);
    }

    @Override
    public int insertSold(SoldDto sold) {
        return purchaseMapper.insertSold(sold);
    }

    //사용하지 않음.
    @Override
    public int modifyOptionStock(ProductOptionDto productOptionDto) {
        return purchaseMapper.modifyOptionStock(productOptionDto);
    }

    @Override
    public int stackMileage(MileageDto mileageDto) {
        return purchaseMapper.stackMileage(mileageDto);
    }

    @Override
    public List<ProductOptionDto> getProductOption(ProductOptionDto productOptionDto) {

        List<ProductOptionDto> productOptionDtoList = new ArrayList<>();
        //중간에 삭제하여 null 인경우는 삭제해야함.
        for (int i = 0 ; i<productOptionDto.getOptionList().size(); i++){

            if(productOptionDto.getOptionList().get(i).getOptionId()==0){
                productOptionDto.getOptionList().remove(i);
                i--;
            }else {
                productOptionDtoList.add(purchaseMapper.getProductOption(productOptionDto.getOptionList().get(i)));
                productOptionDtoList.get(i).setOptionQuantity(productOptionDto.getOptionList().get(i).getOptionQuantity());
            }
        }
        return productOptionDtoList;
    }

    @Override
    public String getUserEmail(int userId) {
        return purchaseMapper.getUserEmail(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor={Exception.class})
    public int orderinsert(String orderDto, String soldDto, String addressDto, String usedMileageDto, String stackMileageDto, String optionDto) {

        OrderDto order = jsonToObject.converToOrder(jsonConvertor.jsonConvertor(orderDto));
        SoldDto soldDtos = jsonToObject.convertToSoldList(jsonConvertor.jsonArrayConvertor(soldDto));
        AddressDto address = jsonToObject.convertToAddressDto(jsonConvertor.jsonConvertor(addressDto));
        ProductOptionDto productOptionDto = jsonToObject.convertToOptionList(jsonConvertor.jsonArrayConvertor(optionDto));
        MileageDto usedmileage =  jsonToObject.convertToMileage(jsonConvertor.jsonConvertor(usedMileageDto));
        MileageDto stackMileage = jsonToObject.convertToMileage(jsonConvertor.jsonConvertor(stackMileageDto));


        // 주소가 새로 입력되었을경우 주소부터 insert 함
        if(address.getAddrReloadCheck().equals("reloaded")){
            purchaseMapper.createAddress(encryptAddress.encryption(address));
        }
        purchaseMapper.createOrder(encryptOrder.encryptOrder(order));

        if(!usedmileage.getMileage().equals("0")){
            purchaseMapper.insertMileage(usedmileage);
        }
        if(!stackMileage.getMileage().equals("0")){
            purchaseMapper.stackMileage(stackMileage);
        }
        purchaseMapper.insertSold_v2(soldDtos.getSoldList());

        for (int i = 0 ; i <soldDtos.getSoldList().size(); i++){
            purchaseMapper.modifyOptionStock(productOptionDto.getOptionList().get(i));
        }


        return 1;
    }
}
