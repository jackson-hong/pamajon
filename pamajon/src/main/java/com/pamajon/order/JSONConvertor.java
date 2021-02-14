package com.pamajon.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JSONConvertor {

    public Map<String,String> jsonConvertor(String json){

        Map<String,String> orderMap = new HashMap<>();
        try {
            orderMap = new ObjectMapper().readValue(json,Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return orderMap;
    }

    public List<Map<String,String>> jsonArrayConvertor(String json){

        List<Map<String, String>> info = new Gson().fromJson(String.valueOf(json),
                new TypeToken<List<Map<String, String>>>(){}.getType());

        return info;
    }
}
