package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.ReviewDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reviewDaoImpl")
@Primary
public class ReviewDaoImpl implements ReviewDao{
    @Override
    public List<ReviewDto> listReview(SqlSession session, List<ReviewDto> list) {
        return null;
    }

    @Override
    public int createReview(SqlSession session, ReviewDto reviewDto) {
        return 0;
    }

    @Override
    public int readReview(SqlSession session, int ReviewId) {
        return 0;
    }

    @Override
    public int updateReview(SqlSession session, ReviewDto reviewDto) {
        return 0;
    }

    @Override
    public int deleteReview(SqlSession session, int reviewId) {
        return 0;
    }

    @Override
    public int hitPlusReview(SqlSession session, int reviewId) {
        return 0;
    }
}
