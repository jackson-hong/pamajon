package com.pamajon.board.model.service;

import com.pamajon.board.model.vo.ReviewDto;

import java.util.List;

public interface ReviewService {
    /*Review*/
    //Review 목록보기
    List<ReviewDto> listReview(List<ReviewDto> list);
    //Review CRUD
    int createReview(ReviewDto reviewDto);
    int readReview(int ReviewId);
    int updateReview(ReviewDto reviewDto);
    int deleteReview(int reviewId);
    //Review 조회수
    int hitPlusReview(int reviewId);
}
