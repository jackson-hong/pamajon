package com.pamajon.order.model.vo;

import lombok.Data;

@Data
public class CartListDto {
    private int sbId;
    private int optionId;
    private int productId;
    private String productName;
    private String optionColor;
    private String optionSize;
    private int optionQuantity;
    private String productThumbnailImage;
    private String optionCode;
    private String productPrice;
}
