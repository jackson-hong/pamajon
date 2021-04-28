package com.pamajon.order.model.vo;

import com.pamajon.common.security.AES256Util;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Member {

    private int userId;
    private String memberName;
    private String memberPhone;
    private String memberEmail;
    private String memberGrade;
    private String memberDate;
    private String memberStatus;
    private AES256Util aes256Util;

    }

