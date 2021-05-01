package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnaDaoImpl implements QnaDao {

    @Override
    public List<QnaDto> listQna(SqlSession session) {
        return session.selectList("qna.selectQnaList");
    }

    @Override
    public int createQna(SqlSession session, QnaDto qnaDto) {return session.insert("qna.createQna" , qnaDto); }

    @Override
    public QnaDto readQna(SqlSession session, int qnaId) { return session.selectOne("qna.readQna",qnaId); }

    @Override
    public int updateQna(SqlSession session, int qnaId) {
        return session.update("qna.updateQna",qnaId);
    }

    @Override
    public int deleteQna(SqlSession session, int qnaId) {
        return session.delete("qna.deleteQna",qnaId);
    }

    @Override
    public  String getWriterName(SqlSession session, int userId){ return  session.selectOne("qna.getWriterName" , userId);}
}
