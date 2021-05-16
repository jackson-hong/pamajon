package com.pamajon.member.model.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberAddr {
    private int addrId;
    private String addrCellPhone;
    private int userId;
    private String addrName;
    private String addrReceiver;
    private String addrZipcode;
    private String addr;
    private String addrDetail;
    private String addrPhone;
    private boolean addrStatus;
}
