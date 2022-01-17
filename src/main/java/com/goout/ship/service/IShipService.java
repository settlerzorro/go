package com.goout.ship.service;

import com.alibaba.fastjson.JSONObject;
import com.goout.ship.entity.Ship;

import java.util.List;

public interface IShipService {

    public List<Ship> getShipList(Integer userId);

    public boolean like(Integer userId,Integer busId);

    public boolean dislike(Integer id);

    public boolean insertComment(JSONObject requestBody);

    public boolean deleteComment(Integer id);

    public boolean insertShip(Ship ship);

    public boolean deleteShip(Integer id);

    public boolean updateShip(Ship ship);


}
