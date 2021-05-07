package com.pamajon.board.model.service;

import com.pamajon.board.model.vo.BoardDto;
import com.pamajon.board.model.vo.QnaDto;

import java.util.List;

public interface QnaService {
    /*Qna*/
    // Qna 목록보기
    List<QnaDto> listQna();

    //Qna CRUD
    int createQna(QnaDto qnaDto);
    QnaDto readQna(int qnaId);
    int updateQna(int qnaid);
    int deleteQna(int qnaId);


    String getWriterName(int userId);
    BoardDto getProductInfo(int productId);

    int getTotal();
}