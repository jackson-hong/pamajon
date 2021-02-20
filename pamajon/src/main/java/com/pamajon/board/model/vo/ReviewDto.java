package com.pamajon.board.model.vo;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReviewDto {
    private int reviewId;
    private int productId;
    private int userId;
    private String reviewTitle;
    private String reviewContent;
    private Date reviewDate;
    private String reviewPwd;
    private int reviewStatus;
    private int reviewHit;
}
