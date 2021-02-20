package com.pamajon.board.model.vo;

import lombok.*;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class QnaDto {
    private int qnaId;
    private int productId;
    private int userId;
    private String qnaTitle;
    private String qnaContent;
    private Date qnaDate;
    private String qnaPwd;
    private int qnaStatus;
}
