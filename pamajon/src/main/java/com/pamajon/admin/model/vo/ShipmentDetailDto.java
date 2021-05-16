package com.pamajon.admin.model.vo;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ShipmentDetailDto {

    private int orderId;
    private String proImgName;
    private String productName;
    private String productSize;
    private int productQuantity;
    private String orderStatus;
    private int productPrice;
}
