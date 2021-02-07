package com.pamajon.order.model.service;

import com.pamajon.order.model.vo.AddressDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderServiceImpl")
@Primary
public class OrderServiceImpl implements OrderService{

}
