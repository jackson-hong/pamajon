package com.pamajon.common.comparators;

import java.util.Comparator;
import java.util.HashMap;

public class BrandComparator implements Comparator<HashMap<String,String>> {

    private final String key;

    public BrandComparator(String key){
        this.key = key;
    }
    @Override
    public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
        return o1.get(key).compareTo(o2.get(key));
    }

}