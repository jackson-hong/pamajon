package com.pamajon.order.model.vo;

import com.pamajon.common.security.AES256Util;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Member {

    private int userId;
    private String memberName;
    private String memberPhone;
    private String memberGrade;
    private String memberDate;
    private String memberStatus;
}
