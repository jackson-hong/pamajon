package com.pamajon.order.model.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Member {

    private int userId;
    private String memberName;
    private String memberAddress;
    private String memberEmail;
    private String memberGrade;
}
