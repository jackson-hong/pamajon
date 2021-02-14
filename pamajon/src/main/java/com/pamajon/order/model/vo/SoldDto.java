package com.pamajon.order.model.vo;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SoldDto {

    private int soldId;
    private String orderId;
    private String optionId;
    private String soldQuantity;
    private List<SoldDto> soldList;

    public SoldDto(String optionId, String soldQuantity) {
        this.optionId = optionId;
        this.soldQuantity = soldQuantity;
    }
}
