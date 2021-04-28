package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

public interface QnaDao {
    //Q&A
    // Qna 목록보기
    List<QnaDto> listQna(SqlSession session);

    //Qna CRUD
    int createQna(SqlSession session, QnaDto qnaDto);
    int readQna(SqlSession session, int qnaId);
    int updateQna(SqlSession session, QnaDto qnaDto);
    int deleteQna(SqlSession session, int qnaId);
}
