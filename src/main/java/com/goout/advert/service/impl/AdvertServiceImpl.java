package com.goout.advert.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goout.advert.dao.AdvertMapper;
import com.goout.advert.entity.Advert;
import com.goout.advert.service.IAdvertService;
import com.goout.api.dao.StationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("advertService")
public class AdvertServiceImpl implements IAdvertService {
    private static final Logger logger = LoggerFactory.getLogger(AdvertServiceImpl.class);

    @Autowired
    private AdvertMapper busMapper;


    @Override
    public List<Advert> getAdvertList() {
        return busMapper.select();
    }

    @Override
    public boolean insertAdvert(Advert air) {
        return busMapper.insertAdvert(air);
    }

    @Override
    public boolean deleteAdvert(Integer id) {
        return busMapper.deleteAdvert(id);
    }

}
