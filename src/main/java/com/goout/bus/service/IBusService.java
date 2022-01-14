package com.goout.bus.service;

import com.alibaba.fastjson.JSONObject;
import com.goout.bus.entity.Bus;

import java.util.List;

public interface IBusService {

    public List<Bus> getBusList(Integer userId, Bus bus);

    public boolean like(Integer userId,Integer busId);

    public boolean dislike(Integer id);

    public boolean insertComment(JSONObject requestBody);

    public boolean deleteComment(Integer id);

    public boolean insertBus(Bus bus);

    public boolean deleteBus(Integer id);

    public boolean updateBus(Bus bus);


}
