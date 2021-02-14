package com.pamajon.order.model.vo;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductDto {
    private int productId;
    private String productBrand;
    private String productCategory;
    private String productName;
    private String productPrice;
    private String productContent;
    private String productShipFee;
    private String productSale; //할인율
    private String productFrom;
    private String productMaterial;
    private String productStatus;
    private List<ProductDto> productList;

}
