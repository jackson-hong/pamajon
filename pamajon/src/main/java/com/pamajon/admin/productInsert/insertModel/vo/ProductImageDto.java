package com.pamajon.admin.productInsert.insertModel.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductImageDto {

   private String productImageId;
   private String productId;
   private String productFileName;
   private String productImagePath;
   private String productImageDefaultValue;
}
