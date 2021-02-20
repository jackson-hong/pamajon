package com.pamajon.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    int userId;
    String memId;
    String memPwd;
    String memPwdcheckQ;
    String memPwdcheckA;
    String memName;
    String memAddress;
    String memPhone;
    String memEmail;
    String memGrade;
    Date memDate;
    boolean memStatus;
}
