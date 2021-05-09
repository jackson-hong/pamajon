package com.pamajon.common.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PageInfo {

    private int listCount;
    private int currentPage;
    private int startPage;
    private int endPage;
    private int maxPage;
    private int pageLimit;
    private int boardLimit;



}
