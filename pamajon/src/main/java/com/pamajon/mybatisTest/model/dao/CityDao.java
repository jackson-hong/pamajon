package com.pamajon.mybatisTest.model.dao;

import com.pamajon.mybatisTest.model.vo.City;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CityDao {

    @Autowired
    SqlSessionTemplate sqlSession;

    public ArrayList<City> selectCities(){

        return (ArrayList)sqlSession.selectList("mallDetailView.citySelector");
    }
}
