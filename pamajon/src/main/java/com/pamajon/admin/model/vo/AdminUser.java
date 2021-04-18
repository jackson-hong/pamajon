package com.pamajon.admin.model.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AdminUser {

    private int adminId;
    private String authId;
    private String deptCd;
    private String adminLoginId;
    private String adminLoginPwd;
    private String adminTelFdigit;
    private String adminTelSdigit;
    private String adminTelTdigit;
    private String adminBirth;
    private String adminEmail;
    private String adminDomain;
    private String adminToken;
    private String adminDate;
    private String adminCurrDate; //최근 접속일 가져옴.
    private String adminApprStatus; //계정 상태 저장
    private Integer adminFailCount; // 5회 실패시 계정 블락
    private String rememberPwd; //
    private String sessionKey;
    private String sessionLimit;


}
