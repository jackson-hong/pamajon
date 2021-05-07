package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.BoardDto;
import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

public interface QnaDao {
    //Q&A
    // Qna 목록보기
    List<QnaDto> listQna(SqlSession session);

    //Qna CRUD
    int createQna(SqlSession session, QnaDto qnaDto);
    QnaDto readQna(SqlSession session, int qnaId);
    int updateQna(SqlSession session, int qnaId);
    int deleteQna(SqlSession session, int qnaId);

    //Qna get Data from another table
    String getWriterName(SqlSession session, int userId);
    BoardDto getProductInfo(SqlSession session, int productId);

    int getTotal(SqlSession session);
}
