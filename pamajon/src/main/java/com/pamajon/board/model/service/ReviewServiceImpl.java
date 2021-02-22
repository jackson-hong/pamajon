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
    public List<ReviewDto> listReview(List<ReviewDto> list) {
        return null;
    }

    @Override
    public int createReview(ReviewDto reviewDto) {
        return 0;
    }

    @Override
    public int readReview(int ReviewId) {
        return 0;
    }

    @Override
    public int updateReview(ReviewDto reviewDto) {
        return 0;
    }

    @Override
    public int deleteReview(int reviewId) {
        return 0;
    }

    @Override
    public int hitPlusReview(int reviewId) {
        return 0;
    }
}
