package com.pamajon.board.model.dao;

import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

public interface Qnadao {
    /*Qna*/
    // Qna 목록보기
    List<QnaDto> listQna(SqlSession session, List<QnaDto> list);

    //Qna CRUD
    int createQna(SqlSession session, QnaDto qnaDto);
    int readQna(SqlSession session, int qnaId);
    int updateQna(SqlSession session, QnaDto qnaDto);
    int deleteQna(SqlSession session, int qnaId);

    // Qna 조회수
    int hitPlusQna(SqlSession session, int qnaId);
}
