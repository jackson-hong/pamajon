package com.pamajon.mapper;

import com.pamajon.product.model.vo.BrandDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    public List<BrandDto> getBrandList();

    public int insertBrand(BrandDto brandDto);
}
