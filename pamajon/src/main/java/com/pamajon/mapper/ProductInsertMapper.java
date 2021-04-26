package com.pamajon.mapper;

import com.pamajon.admin.productInsert.insertModel.vo.ProductCategory;
import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;
import com.pamajon.admin.productInsert.insertModel.vo.WrapperCategory;
import com.pamajon.common.vo.CommonCodeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductInsertMapper {
    public List<ProductDto> getBrand();
    public List<WrapperCategory> getWrapperCategory();
    public List<ProductCategory> getCategory(int wrapperId);
    List<CommonCodeDto> getOrigin();
    List<CommonCodeDto> getSizeOption(String sizeOption);
    int insertProduct(ProductDto productDto);
    int optionInsert(ProductOptionDto productOptionDto);
    void insertProductImages(HashMap<String, String> fileMap);
}
