package com.pamajon.admin.productInsert.insertModel.vo;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class BrandDto {

    private String brandId;
    private String brandName;
}
