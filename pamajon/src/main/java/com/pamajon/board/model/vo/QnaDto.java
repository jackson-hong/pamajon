package com.pamajon.board.model.vo;

import lombok.*;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class QnaDto {
    private int qnaId;
    private int productId;
    private int userId;
    private String MemName;
    private String ProductName;
    private String qnaTitle;
    private String qnaContent;
    private Date qnaDate;
    private Date qnaModifyDate;
    private String qnaPwd;
    private int qnaStatus;
}
