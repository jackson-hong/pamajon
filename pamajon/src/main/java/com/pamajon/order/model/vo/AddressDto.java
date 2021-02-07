package com.pamajon.order.model.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AddressDto {

    private int addrId;
    private String userId;
    private String addrName;
    private String addrReceiver;
    private String addrZipcode;
    private String addr;
    private String addrDetail;
    private String [] mobile;
    private String [] phone;
    private String addrPhone;
    private String addrCellPhone;
    private String addrStatus;
    private String regularCheck;

}
