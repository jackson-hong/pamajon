package com.pamajon.admin.productInsert.insertModel.vo;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ProductDto {
    private int productId;
    private String productBrand;
    private String productCategory;
    private String productName;
    private String productPrice;
    private String productOrigin;
    private String productContent;
    private String productShipFee;
    private String productColor;
    private String productSale; //할인율
    private String productFrom;
    private String productMaterial;
    private String productStatus;
    private String productDate; // 입력일
    private String productBigCateId;
    private List<com.pamajon.order.model.vo.ProductDto> productList;
}
