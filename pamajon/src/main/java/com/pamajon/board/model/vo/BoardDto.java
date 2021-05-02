package com.pamajon.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
    private int qnaId;
    private int userId;
    private String productPictureRoot;
    private String productName;
    private String MemName;
    private String qnaTitle;
    private String qnaContent;
    private Date qnaModifyDate;
    private String qnaPwd;
    private int qnaStatus;
}
