package com.pamajon.board.model.service;

import com.pamajon.board.model.vo.QnaDto;
import com.pamajon.board.model.vo.ReviewDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface QnaService {
    /*Qna*/
    // Qna 목록보기
    List<QnaDto> listQna();

    //Qna CRUD
    int createQna(QnaDto qnaDto);

    int readQna(int qnaId);

    int updateQna(QnaDto qnaDto);

    int deleteQna(int qnaId);
}