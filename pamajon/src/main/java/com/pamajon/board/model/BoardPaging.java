package com.pamajon.board.model;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class BoardPaging {
    private int currentPageNo;      // 현재 페이지
    private int viewDataNo;         // 페이지에 출력할 데이터 개수
    private int pageSize;           // 하단에 출력될 페이지 사이즈
    private String searchType;      // 검색타입
    private String searchKeyword;   // 검색키워드

    public BoardPaging() {
        this.currentPageNo = 1;
        this.viewDataNo = 10;
        this.pageSize = 10;
    }

    public int getStartPage(){
        return (currentPageNo - 1) * viewDataNo;
    }
}
