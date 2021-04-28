package com.pamajon.product.model.vo;

import lombok.*;

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
}
