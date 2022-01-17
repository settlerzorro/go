package com.goout.ship.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goout.ship.dao.ShipMapper;
import com.goout.ship.dao.CommentShipMapper;
import com.goout.ship.dao.LikeShipMapper;
import com.goout.ship.entity.Ship;
import com.goout.ship.entity.CommentShip;
import com.goout.ship.service.IShipService;
import com.goout.api.dao.StationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shipService")
public class ShipServiceImpl implements IShipService {
    private static final Logger logger = LoggerFactory.getLogger(ShipServiceImpl.class);

    @Autowired
    private ShipMapper shipMapper;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private LikeShipMapper likeShipMapper;

    @Autowired
    private CommentShipMapper commentShipMapper;




    public List<Ship> getShipList(Integer userId) {
        List<Ship> list = shipMapper.selectAll();
        for(Ship bu : list){
            bu.setCommentShips(commentShipMapper.selectByBusId(bu.getId()));
            if(userId != null){
                bu.setLikeShips(likeShipMapper.selectByShipIdAndUserId(bu.getId(),userId));
            }
        }
        return list;
    }


    @Override
    public boolean like(Integer userId,Integer shipId) {
        return likeShipMapper.insertLikeShip(userId,shipId);
    }

    @Override
    public boolean dislike(Integer id) {
        return likeShipMapper.deleteLikeShip(id);
    }

    @Override
    public boolean insertComment(JSONObject requestBody) {
        CommentShip ct = requestBody.toJavaObject(CommentShip.class);
        return commentShipMapper.insert(ct);
    }

    @Override
    public boolean deleteComment(Integer id) {
        return commentShipMapper.deleteById(id);
    }

    @Override
    public boolean insertShip(Ship ship) {
        return shipMapper.insertShip(ship);
    }

    @Override
    public boolean deleteShip(Integer id) {
        return shipMapper.deleteShip(id);
    }

    @Override
    public boolean updateShip(Ship ship) {
        return shipMapper.updateShip(ship);
    }
}
