package com.pamajon.admin.model.vo;

import lombok.*;

import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MonthlyRateDto{

    private int month;
    private int total;

}
