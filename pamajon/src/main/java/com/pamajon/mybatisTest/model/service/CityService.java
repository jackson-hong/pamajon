package com.pamajon.mybatisTest.model.service;

import com.pamajon.mybatisTest.model.dao.CityDao;
import com.pamajon.mybatisTest.model.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CityService {

    @Autowired
    private CityDao cDao;

    public ArrayList<City> selectCities(){
        return cDao.selectCities();
    }
}
