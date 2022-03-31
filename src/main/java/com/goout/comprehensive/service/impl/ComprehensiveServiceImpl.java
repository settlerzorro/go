package com.goout.comprehensive.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goout.comprehensive.dao.ComprehensiveMapper;
import com.goout.comprehensive.entity.Comprehensive;
import com.goout.comprehensive.service.IComprehensiveService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("comprehensiveService")
public class ComprehensiveServiceImpl implements IComprehensiveService {
    private static final Logger logger = LoggerFactory.getLogger(ComprehensiveServiceImpl.class);

    @Autowired
    private ComprehensiveMapper comprehensiveMapperMapper;

    public List<Comprehensive> getComprehensive(String cityName, String date) {
        List<Comprehensive> list = comprehensiveMapperMapper.selectByCityNameAndDate(cityName,date);
        return list;
    }

    @Override
    public boolean insertComprehensive(JSONObject requestBody) {
        Comprehensive ct = requestBody.toJavaObject(Comprehensive.class);
        return comprehensiveMapperMapper.insert(ct);
    }

    @Override
    public boolean deleteComprehensive(Integer id) {
        return comprehensiveMapperMapper.delete(id);
    }


    @Override
    public boolean updateComprehensive(Comprehensive bus) {
        return comprehensiveMapperMapper.update(bus);
    }

    @Override
    public List<Comprehensive> selectAll() {
        return comprehensiveMapperMapper.selectAll();
    }

    @Override
    public List<Comprehensive> selectByCityName(String cityName) {
        return comprehensiveMapperMapper.selectByCityName(cityName);
    }

    @Override
    public List<Comprehensive> selectByDate(String date) {
        return comprehensiveMapperMapper.selectByDate(date);
    }
}
