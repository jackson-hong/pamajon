package com.pamajon.mallDetail.model.service;

import com.pamajon.mapper.MalldetailMapper;
import com.pamajon.order.model.vo.ProductOptionDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallDetailServiceImpl implements MallDetailSerivce{

    private final MalldetailMapper malldetailMapper;

    public MallDetailServiceImpl(MalldetailMapper malldetailMapper) {
        this.malldetailMapper = malldetailMapper;
    }

}
