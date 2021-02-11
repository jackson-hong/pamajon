package com.pamajon.order.model.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MileageDto {

    private int mileId;
    private String userId;
    private int mileage;
    private String mileageContent;

}
