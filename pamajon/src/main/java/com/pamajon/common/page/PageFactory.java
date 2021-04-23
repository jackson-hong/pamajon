package com.pamajon.common.page;

public class PageFactory {
    public static String getPageBar(int totalData, int cPage, int numPerPage) {

        String pageBar = "";

        int pageBarSize = 5;

        int totalPage = (int) Math.ceil((double) totalData / numPerPage);

        int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
        int pageEnd = pageNo + pageBarSize - 1;
        pageBar += "<div id=\"pagination\">";
        if (pageNo == 1) {
            pageBar += "<button type=\"button\" class=\"page-btn disable\" id=\"left\"><</button>";
        } else {
            pageBar += "<button onclick='fn_paging(" + (pageNo - 1) + ")' type=\"button\" class=\"page-btn\" id=\"left\"><</button>";
        }

        while (!(pageNo > pageEnd || pageNo > totalPage)) {
            if (pageNo == cPage) {
                pageBar += "<button class=\"page-btn selected\" id=\"num-page\">"+pageNo+"</button>";
            } else {
                pageBar += "<button onclick='fn_paging(" + pageNo + ")' type=\"button\" class=\"page-btn\" id=\"num-page\">"+pageNo+"</button>";
            }
            pageNo++;
        }

        if (pageNo > totalPage) {
            pageBar += "<button class=\"page-btn disable\" id=\"right\">></button>";
        } else {
            pageBar += "<button onclick='fn_paging(" + pageNo + ")' type=\"button\" class=\"page-btn\" id=\"right\"><</button>";
        }
        pageBar += "</div>";

//        pageBar += "<script>";
//        pageBar += "function fn_paging(cPage){";
//        pageBar += "location.href='" + uri + "?cPage='+cPage;";
//        pageBar += "}";
//        pageBar += "</script>";

        return pageBar;
    }
}
