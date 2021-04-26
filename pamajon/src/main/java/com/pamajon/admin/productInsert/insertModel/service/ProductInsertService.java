package com.pamajon.admin.productInsert.insertModel.service;

import com.pamajon.admin.productInsert.insertModel.vo.ProductCategory;
import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;
import com.pamajon.admin.productInsert.insertModel.vo.WrapperCategory;
import com.pamajon.common.vo.CommonCodeDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ProductInsertService {
    public List<ProductDto> getBrand();

    List<WrapperCategory> getWrapperCategory();

    List<ProductCategory> getCategory(int wrapperId);

    List<CommonCodeDto> getOrigin();

    List<CommonCodeDto> getSizeOption(String sizeOption);

    int insertProduct(ProductDto productDto);

    int optionInsert(ArrayList<ProductOptionDto> optionList);

    void insertProductImages(HashMap<String, String> fileMap);
}
