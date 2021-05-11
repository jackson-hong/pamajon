package com.pamajon.admin.model.vo;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SearchParameterDto {

    private int pageNum;
    private String searchOption;
    private String startDate;
    private String endDate;
    private String searchDateOptionCheckBox;
    private String searchOrderValue;

}
