package com.pamajon.admin.model.vo;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SearchParameterDto {

    private String pageNum;
    private String searchOption;
    private String startDate;
    private String endDate;
    private String searchDateOptionCheckBox;
    private String searchOrderValue;

    public SearchParameterDto(String pageNum, String searchOption, String startDate, String endDate, String searchDateOptionCheckBox, String searchOrderValue) {
        this.pageNum = pageNum;
        this.searchOption = searchOption;
        this.startDate = startDate;
        this.endDate = endDate;
        this.searchDateOptionCheckBox = searchDateOptionCheckBox;
        this.searchOrderValue = searchOrderValue;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSearchDateOptionCheckBox() {
        return searchDateOptionCheckBox;
    }

    public void setSearchDateOptionCheckBox(String searchDateOptionCheckBox) {
        this.searchDateOptionCheckBox = searchDateOptionCheckBox;
    }

    public String getSearchOrderValue() {
        return searchOrderValue;
    }

    public void setSearchOrderValue(String searchOrderValue) {
        this.searchOrderValue = searchOrderValue;
    }

    @Override
    public String toString() {
        return "SearchParameterDto{" +
                "pageNum='" + pageNum + '\'' +
                ", searchOption='" + searchOption + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", searchDateOptionCheckBox='" + searchDateOptionCheckBox + '\'' +
                ", searchOrderValue='" + searchOrderValue + '\'' +
                '}';
    }
}
