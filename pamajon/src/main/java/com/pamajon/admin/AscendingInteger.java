package com.pamajon.admin;

import com.pamajon.admin.model.vo.MonthlyRateDto;

import java.util.Comparator;

public class AscendingInteger implements Comparator<MonthlyRateDto>{


    @Override
    public int compare(MonthlyRateDto o1, MonthlyRateDto o2) {

        if(o1.getMonth()>o2.getMonth()) return 1;
        if(o1.getMonth()<o2.getMonth()) return -1;
        return 0;
    }
}
