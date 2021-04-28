package com.pamajon.order.model.vo;

import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductOptionDto {

    private int optionId;
    private int productId;
    private String productName;
    private String optionColor;
    private String optionSize;
    private int optionQuantity;
    private String productOptionSalesRate;
    private String productOptionStatus;
    private String productThumbnailImage;
    private String optionCode;
    private String productPrice;
    private ArrayList<ProductOptionDto> optionList;
}
