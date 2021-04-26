package com.pamajon.admin.productInsert.insertModel.vo;

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
    private String optionQuantityStatus;
    private String optionCode;
    private String productPrice;
    private ArrayList<ProductOptionDto> optionList;
}
