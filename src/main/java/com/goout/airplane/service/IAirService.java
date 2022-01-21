package com.goout.airplane.service;

import com.alibaba.fastjson.JSONObject;
import com.goout.airplane.entity.Air;

import java.util.List;

public interface IAirService {

    public List<Air> getAirList(Integer userId, Air air);

    public boolean like(Integer userId,Integer airId);

    public boolean dislike(Integer id);

    public boolean insertComment(JSONObject requestBody);

    public boolean deleteComment(Integer id);

    public boolean insertAir(Air air);

    public boolean deleteAir(Integer id);

    public boolean updateAir(Air air);

    public List<Air> selectAll();


}
