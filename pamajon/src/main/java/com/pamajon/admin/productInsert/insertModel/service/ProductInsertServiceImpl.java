package com.pamajon.admin.productInsert.insertModel.service;

import com.pamajon.admin.productInsert.insertModel.vo.ProductCategory;
import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;
import com.pamajon.admin.productInsert.insertModel.vo.WrapperCategory;
import com.pamajon.common.vo.CommonCodeDto;
import com.pamajon.mapper.ProductInsertMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Log4j2
@Service("productInsertServiceImpl")
@Primary
public class ProductInsertServiceImpl implements ProductInsertService{

    @Autowired
    private  ProductInsertMapper productInsertMapper;

    @Override
    public List<ProductDto> getBrand() {
        return productInsertMapper.getBrand();
    }

    @Override
    public List<WrapperCategory> getWrapperCategory() {
        return productInsertMapper.getWrapperCategory();
    }

    @Override
    public List<ProductCategory> getCategory(int wrapperId) {
        return productInsertMapper.getCategory(wrapperId);
    }

    @Override
    public List<CommonCodeDto> getOrigin() {
        return productInsertMapper.getOrigin();
    }

    @Override
    public List<CommonCodeDto> getSizeOption(String sizeOption) {
        return productInsertMapper.getSizeOption(sizeOption);
    }

    @Override
    public int insertProduct(ProductDto productDto) {
        return productInsertMapper.insertProduct(productDto);
    }

    @Override
    public int optionInsert(ArrayList<ProductOptionDto> optionList) {

        //먼저 들어온 옵션 삭제 작업이 필요.
        for(int i = 0; i<optionList.size();i++){
            if(optionList.get(i)==null){
                optionList.remove(i);
                i--;
            }
        }
        for (int i = 0 ; i<optionList.size(); i++){

            if(productInsertMapper.optionInsert(optionList.get(i)) == 0){
                return 0;
            }
        }
        return 1;
    }

    @Override
    public void insertProductImages(HashMap<String, String> fileMap) {

        productInsertMapper.insertProductImages(fileMap);
    }
}
