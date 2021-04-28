package com.pamajon.board.model.vo;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {
    private int reviewId;
    private String productId;
    private String userId;
    private String reviewTitle;
    private String reviewContent;
    private Date reviewDate;
    private Date reviewModifyDate;
    private String reviewPwd;
    private int reviewStatus;
    private int reviewHit;
}
