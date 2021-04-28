package com.pamajon.mapper;

import com.pamajon.board.model.vo.QnaDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {

    public int createQna(QnaDto qnaDto);

    public QnaDto readQna(int qnaId);

    public int updateQna(int qnaId);

    public int deleteQna(int qnaId);
}
