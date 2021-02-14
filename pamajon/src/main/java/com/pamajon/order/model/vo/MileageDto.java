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
    private String mileage;
    private String mileageContent;

}
