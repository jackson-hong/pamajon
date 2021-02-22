package com.pamajon.board.model.service;

import com.pamajon.board.model.dao.ReviewDao;
import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("qnaServiceImpl")
@Primary
public class QnaServiceImpl implements QnaService {

    @Qualifier("reviewDaoImpl")
    ReviewDao dao;
    SqlSession session;


    public QnaServiceImpl(ReviewDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public List<QnaDto> listQna(List<QnaDto> list) {
        return null;
    }

    @Override
    public int createQna(QnaDto qnaDto) {
        return 0;
    }

    @Override
    public int readQna(int qnaId) {
        return 0;
    }

    @Override
    public int updateQna(QnaDto qnaDto) {
        return 0;
    }

    @Override
    public int deleteQna(int qnaId) {
        return 0;
    }

    @Override
    public int hitPlusQna(int qnaId) {
        return 0;
    }
}
