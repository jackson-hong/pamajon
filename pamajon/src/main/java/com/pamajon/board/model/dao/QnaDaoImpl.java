package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qnaDaoImpl")
@Primary
public class QnaDaoImpl implements Qnadao{

    @Override
    public List<QnaDto> listQna(SqlSession session, List<QnaDto> list) {
        return null;
    }

    @Override
    public int createQna(SqlSession session, QnaDto qnaDto) {
        return 0;
    }

    @Override
    public int readQna(SqlSession session, int qnaId) {
        return 0;
    }

    @Override
    public int updateQna(SqlSession session, QnaDto qnaDto) {
        return 0;
    }

    @Override
    public int deleteQna(SqlSession session, int qnaId) {
        return 0;
    }

    @Override
    public int hitPlusQna(SqlSession session, int qnaId) {
        return 0;
    }
}
