package com.goout.comprehensive.service;

import com.alibaba.fastjson.JSONObject;
import com.goout.bus.entity.Bus;
import com.goout.comprehensive.entity.Comprehensive;

import java.util.List;

public interface IComprehensiveService {


    public boolean insertComprehensive(JSONObject requestBody);

    public boolean deleteComprehensive(Integer id);

    public boolean updateComprehensive(Comprehensive bus);

    public List<Comprehensive> getComprehensive(String cityName,String date);

    public List<Comprehensive> selectAll();

    public List<Comprehensive> selectByCityName(String cityName);

    public List<Comprehensive> selectByDate(String date);
}
