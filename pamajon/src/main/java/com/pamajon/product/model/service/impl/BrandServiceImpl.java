package com.pamajon.product.model.service.impl;

import com.pamajon.mapper.ProductMapper;
import com.pamajon.product.model.service.BrandService;
import com.pamajon.product.model.vo.BrandDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BrandServiceImpl")
@Primary
public class BrandServiceImpl implements BrandService {

    private final ProductMapper productMapper;

    public BrandServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<BrandDto> getBrandList() { return productMapper.getBrandList(); }

    @Override
    public int insertBrand(BrandDto brandDto) { return productMapper.insertBrand(brandDto); }
}
