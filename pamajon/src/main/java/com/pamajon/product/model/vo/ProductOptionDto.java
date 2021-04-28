package com.pamajon.product.model.vo;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductOptionDto {

    private int productOptionId;
    private String productId;
    private String productOptionSize;
    private String productOptionQuantity;
    private String productOptionSalesRate;
    private String productOptionStatus;
    private List<ProductOptionDto> optionDtoList;
}
