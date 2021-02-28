package com.pamajon.board.model.service;

import com.pamajon.board.model.QnaDao;
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
    private final QnaDao dao;
    private final SqlSession session;

    public QnaServiceImpl(QnaDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public List<QnaDto> listQna() { return dao.listQna(session); }

    @Override
    public int createQna(QnaDto qnaDto) {
        return dao.createQna(session,qnaDto);
    }

    @Override
    public int readQna(int qnaId) {
        return dao.readQna(session,qnaId);
    }

    @Override
    public int updateQna(QnaDto qnaDto) {
        return dao.updateQna(session,qnaDto);
    }

    @Override
    public int deleteQna(int qnaId) {
        return dao.deleteQna(session,qnaId);
    }

    @Override
    public int hitPlusQna(int qnaId) {
        return dao.hitPlusQna(session,qnaId);
    }
}
