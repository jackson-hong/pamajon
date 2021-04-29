package com.pamajon.mapper;

import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {

    List <QnaDto> listQna();
    int createQna(QnaDto qnaDto);
    QnaDto readQna(int qnaId);
    int updateQna(int qnaId);
    int deleteQna(int qnaId);
}
