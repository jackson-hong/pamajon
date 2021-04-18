package com.pamajon.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    int userId;
    String memEmail;
    String memPwd;
    String memName;
    String memPhone;
    String memGrade;
    Date memDate;
    boolean memStatus;
    int isSocial;
}
