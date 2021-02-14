package com.pamajon.order;

import com.pamajon.order.model.vo.AddressDto;
import com.pamajon.order.model.vo.MileageDto;
import com.pamajon.order.model.vo.OrderDto;
import com.pamajon.order.model.vo.SoldDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JSONToObject {

    //Map 에서 OrderDto 로 변환.
    public OrderDto converToOrder(Map<String,String> orderObject){

        OrderDto orderDto = new OrderDto();

        orderDto.setUserId(orderObject.get("userId"));
        orderDto.setAddrId(orderObject.get("addrId"));
        orderDto.setOrderAddr(orderObject.get("addr"));
        orderDto.setOrderStatus(orderObject.get("orderStatus"));
        orderDto.setOrderPhone(orderObject.get("orderPhone"));
        orderDto.setOrderEmail(orderObject.get("orderEmail"));
        orderDto.setOrderPurchase(orderObject.get("orderPurchase"));
        orderDto.setOrderMessage(orderObject.get("orderMessage"));
        orderDto.setOrderDeliveryStatus(orderObject.get("orderDeliveryStatus"));
        orderDto.setOrderDate(orderObject.get("orderDate"));
        orderDto.setOrderCardNum(orderObject.get("orderCardNum"));
        orderDto.setOrderTransName(orderObject.get("orderTransName"));
        orderDto.setOrderMethod(orderObject.get("orderMethod"));
        orderDto.setOrderKey(orderObject.get("orderKey"));
        orderDto.setOrderShipfee(orderObject.get("orderShipfee"));

        return orderDto;
    }

    public SoldDto convertToSoldList(List<Map<String,String>> soldList){

        SoldDto soldDto= new SoldDto();
        List<SoldDto> list = new ArrayList<SoldDto>();

        for(int i = 0; i<soldList.size(); i++){

            list.add(new SoldDto(soldList.get(i).get("optionId"),soldList.get(i).get("soldQuantity")));

        }
        soldDto.setSoldList(list);
        return soldDto;
    }

    public AddressDto convertToAddressDto(Map<String,String> addressMap){

        AddressDto addressDto = new AddressDto();
        addressDto.setAddrId(Integer.parseInt(addressMap.get("addrId")));
        addressDto.setUserId(addressMap.get("userId"));
        addressDto.setAddrReceiver(addressMap.get("addrReceiver"));
        addressDto.setAddrZipcode(addressMap.get("addrZipcode"));
        addressDto.setAddrPhone("입력값 없음.");
        addressDto.setAddr(addressMap.get("addr"));
        addressDto.setAddrDetail(addressMap.get("addrDetail"));
        addressDto.setAddrCellPhone(addressMap.get("addrCellPhone"));
        addressDto.setAddrReloadCheck(addressMap.get("addrReloadCheck"));

        return addressDto;
    }

    public MileageDto convertToMileage(Map<String,String> mileageMap){


        MileageDto mileageDto = new MileageDto();
        mileageDto.setUserId(mileageMap.get("userId"));
        if(mileageMap.get("mileage").equals("")||mileageMap.get("mileage")==null){
            mileageDto.setMileage(0+"");
        } else {
            mileageDto.setMileage(mileageMap.get("mileage"));
        }




        return mileageDto;
    }





}
