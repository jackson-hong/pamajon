package com.pamajon.order.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CartDto {
    int sbId;
    int pOptionId;
    int userId;
    int optionQuantity;
}
