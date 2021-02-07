package com.pamajon.order.model.vo;

import lombok.*;

import java.util.LinkedList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ProductOptionDto {

    private int optionId;
    private int productId;
    private String optionColor;
    private String optionSize;
    private int optionQuantity;
    private String optionCode;
    private LinkedList<ProductOptionDto> optionList;
}
