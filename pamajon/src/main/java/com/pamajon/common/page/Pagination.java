package com.pamajon.common.page;

import com.pamajon.common.vo.PageInfo;

public class Pagination {

    public static PageInfo getPageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {

        // * maxPage : lsitCount와 boardLimit 영향을 받는다
        int maxPage = (int)Math.ceil((double)listCount/boardLimit);

        // * startPage : currentPage, pageLimit 영향을 받는다.
        int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

        // * endPage : startPage, PageLimit, maxPage 영향을 받는다.
        int endPage = startPage + pageLimit;
        if(endPage > maxPage) {
            endPage = maxPage;
        }

        return new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);

    }
}
