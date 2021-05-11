package com.pamajon.admin;

import com.pamajon.admin.model.vo.SearchParameterDto;

import java.util.Map;

public class SetSearchParameterDtoFromJSON {

    public static SearchParameterDto converToObject(Map<String,String> json){

        System.out.println(json);
        int i = Integer.parseInt(json.get("pageNum"));

        SearchParameterDto searchParameterDto = new SearchParameterDto();
        searchParameterDto.setPageNum(Integer.parseInt(json.get("pageNum")));
        searchParameterDto.setSearchOption(json.get("searchOption"));
        searchParameterDto.setEndDate(json.get("endDate"));
        searchParameterDto.setStartDate(json.get("startDate"));
        searchParameterDto.setSearchDateOptionCheckBox(json.get("searchDateOptionCheckBox"));
        searchParameterDto.setSearchOrderValue(json.get("searchOrderValue"));
        return searchParameterDto;
    }
}
