package com.pamajon.board.model.service;

import com.pamajon.board.model.dao.QnaDao;
import com.pamajon.board.model.vo.BoardDto;
import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaServiceImpl implements QnaService {

    private final QnaDao dao;
    private final SqlSession session;

    @Autowired
    public QnaServiceImpl(QnaDao dao, SqlSession session) {
        this.dao = dao;
        this.session = session;
    }

    @Override
    public List<QnaDto> listQna() { return dao.listQna(session); }

    @Override
    public int createQna(QnaDto qnaDto) { return dao.createQna(session , qnaDto); }

    @Override
    public QnaDto readQna(int qnaId) {return dao.readQna(session,qnaId); }

    @Override
    public int updateQna(int qnaId) {
        return dao.updateQna(session,qnaId);
    }

    @Override
    public int deleteQna(int qnaId) {
        return dao.deleteQna(session,qnaId);
    }

    @Override
    public String getWriterName(int userId){return dao.getWriterName(session,userId); }

    @Override
    public BoardDto getProductInfo(int productId){return dao.getProductInfo(session,productId);}


}
