package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.ReviewDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface ReviewDao {
    /*Review*/
    //Review 목록보기
    List<ReviewDto> listReview(SqlSession session);
    //Review CRUD
    int createReview(SqlSession session, ReviewDto reviewDto);
    int readReview(SqlSession session, int ReviewId);
    int updateReview(SqlSession session, ReviewDto reviewDto);
    int deleteReview(SqlSession session, int reviewId);

    //Review 조회수
    int hitPlusReview(SqlSession session, int reviewId);
}
