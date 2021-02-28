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
    public List<ReviewDto> listReview(SqlSession session) {
        return session.selectList("review.listReview");
    }

    @Override
    public int createReview(SqlSession session, ReviewDto reviewDto) {
        return session.insert("review.createReview",reviewDto);
    }

    @Override
    public int readReview(SqlSession session, int reviewId) {
        return session.selectOne("review.readReview",reviewId);
    }

    @Override
    public int updateReview(SqlSession session, ReviewDto reviewDto) {
        return session.update("review.updateReview",reviewDto);
    }

    @Override
    public int deleteReview(SqlSession session, int reviewId) {
        return session.delete("review.deleteReview",reviewId);
    }

    @Override
    public int hitPlusReview(SqlSession session, int reviewId) {
        return session.update("review.hitPlusReview",reviewId);
    }
}
