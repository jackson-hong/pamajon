package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qnaDaoImpl")
@Primary
public class QnaDaoImpl implements QnaDao {
    @Override
    public List<QnaDto> listQna(SqlSession session) { return session.selectList("qna.listQna"); }

    @Override
    public int createQna(SqlSession session, QnaDto qnaDto) {return session.insert("qna.createQna" , qnaDto); }

    @Override
    public int readQna(SqlSession session, int qnaId) {
        return session.selectOne("qna.readQna",qnaId);
    }

    @Override
    public int updateQna(SqlSession session, QnaDto qnaDto) {
        return session.update("qna.updateQna",qnaDto);
    }

    @Override
    public int deleteQna(SqlSession session, int qnaId) {
        return session.delete("qna.deleteQna",qnaId);
    }
}
