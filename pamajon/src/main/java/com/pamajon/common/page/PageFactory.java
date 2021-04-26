package com.pamajon.common.page;

public class PageFactory {
    public static String getPageBar(int totalData, int cPage, int numPerPage) {

        String pageBar = "<style>#pagination {\n" +
                "        margin-top: 2em;\n" +
                "        display: flex;\n" +
                "        justify-content: center;\n" +
                "    }\n" +
                "    .page-btn{\n" +
                "        display: flex;\n" +
                "        justify-content: center;\n" +
                "        align-items: center;\n" +
                "        width: 2.5em;\n" +
                "        height: 2.5em;\n" +
                "        letter-spacing: -2px;\n" +
                "        font-weight: bold;\n" +
                "        border-radius: 0;\n" +
                "        background: white;\n" +
                "        border: 1px silver solid;\n" +
                "        border-right: 0;\n" +
                "        color: rgb(100,100,100);\n" +
                "    }\n" +
                "    .page-btn.selected {\n" +
                "        border-bottom:3px rgb(80,80,80) solid;\n" +
                "    }\n" +
                "    .page-btn:last-of-type {\n" +
                "        border-right: 1px silver solid;\n" +
                "    }\n" +
                "    #tbl-cau {\n" +
                "        text-align: left;\n" +
                "    }\n" +
                "\n" +
                "    #tbl-cau th {\n" +
                "        text-indent: 8px;\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "    .fa-li {\n" +
                "        font-size: 14px;\n" +
                "        padding-top: 0;\n" +
                "        margin-top: 0;\n" +
                "    }\n" +
                "    li {\n" +
                "        margin-bottom: 0.5em;\n" +
                "    }\n" +
                "    .page-btn {\n" +
                "        cursor: pointer;\n" +
                "    }\n" +
                "    .selected {\n" +
                "        cursor: auto;\n" +
                "    }\n" +
                "    .disable {\n" +
                "        cursor: auto;\n" +
                "    }</style>";

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
            pageBar += "<button onclick='fn_paging(" + pageNo + ")' type=\"button\" class=\"page-btn\" id=\"right\">></button>";
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
