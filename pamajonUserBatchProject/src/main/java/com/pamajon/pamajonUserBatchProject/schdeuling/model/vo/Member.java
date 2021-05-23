package com.pamajon.pamajonUserBatchProject.schdeuling.model.vo;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private int userId;
    private String lastLoginDate;
    private String memGrade;
    private String memStatus;

}
