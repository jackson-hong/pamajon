package com.pamajon.board.model.service;

import com.pamajon.board.model.dao.ReviewDao;
import com.pamajon.board.model.vo.ReviewDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewServiceImpl")
public class ReviewServiceImpl implements ReviewService {

    @Qualifier("reviewDaoImpl")
    private final ReviewDao dao;
    private final SqlSession session;

    public  ReviewServiceImpl(ReviewDao dao, SqlSession session){
        this.dao = dao;
        this.session = session;
    }

    @Override
    public List<ReviewDto> listReview(List<ReviewDto> list) { return dao.listReview(session); }

    @Override
    public int createReview(ReviewDto reviewDto) { return dao.createReview(session, reviewDto); }

    @Override
    public int readReview(int reviewId) {
        return dao.readReview(session,reviewId);
    }

    @Override
    public int updateReview(ReviewDto reviewDto) {
        return dao.updateReview(session,reviewDto);
    }

    @Override
    public int deleteReview(int reviewId) {
        return dao.deleteReview(session,reviewId);
    }

    @Override
    public int hitPlusReview(int reviewId) {
        return dao.hitPlusReview(session,reviewId);
    }
}
