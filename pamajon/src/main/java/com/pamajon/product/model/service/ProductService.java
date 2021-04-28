package com.pamajon.product.model.service;

import com.pamajon.admin.productInsert.insertModel.vo.ProductDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductImageDto;
import com.pamajon.admin.productInsert.insertModel.vo.ProductOptionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ProductService {
    List<HashMap> homeBoard();
    int wishInsert(HashMap map);
    int wishDuplicate(HashMap map);
    List<HashMap> bigCateList();
    List<HashMap> smallCateList();
    List<HashMap> selectProductByBig(int cateId);
    String selectBigCateName(int cateId);
    List<HashMap> selectProductBySmall(int cateId);
    String selectSmallCateName(int cateId);
    List<HashMap<String,String>> brandList();
    String selectBrandName(int cateId);
    List<HashMap> selectBrand(int cateId);
    List<HashMap> newArrival();
    List<HashMap> productSearch(String key);
    List<ProductImageDto> getImage(int productId);
    List<ProductOptionDto> getOption(int productId);
    ProductImageDto getThumbImg(int productId);
    ProductDto getProduct(int productId);
}
