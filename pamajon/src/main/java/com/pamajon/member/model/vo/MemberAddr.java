package com.pamajon.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberAddr {
    private int addrId;
    private int userId;
    private String addrName;
    private String addrReceiver;
    private String addrZipcode;
    private String addr;
    private String addrDetail;
    private String addrPhone;
    private String addrStatus;
}
