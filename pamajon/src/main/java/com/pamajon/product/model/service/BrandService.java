package com.pamajon.product.model.service;

import com.pamajon.product.model.vo.BrandDto;

import java.util.List;

public interface BrandService {
    public List<BrandDto> getBrandList();
    public int insertBrand(BrandDto brandDto);
}
