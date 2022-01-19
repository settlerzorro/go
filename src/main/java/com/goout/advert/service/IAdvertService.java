package com.goout.advert.service;

import com.alibaba.fastjson.JSONObject;
import com.goout.advert.entity.Advert;

import java.util.List;

public interface IAdvertService {

    public List<Advert> getAdvertList();

    public Boolean showAdvert(String id);
    public Boolean hideAdvert(String id);

    public boolean insertAdvert(Advert air);

    public boolean deleteAdvert(Integer id);



}
