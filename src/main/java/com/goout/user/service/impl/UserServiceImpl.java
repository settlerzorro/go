package com.goout.user.service.impl;

import com.goout.airplane.dao.AirMapper;
import com.goout.airplane.dao.CommentAirMapper;
import com.goout.airplane.dao.LikeAirMapper;
import com.goout.airplane.entity.Air;
import com.goout.airplane.entity.LikeAir;
import com.goout.bus.dao.BusMapper;
import com.goout.bus.dao.CommentBusMapper;
import com.goout.bus.dao.LikeBusMapper;
import com.goout.bus.entity.LikeBus;
import com.goout.ship.dao.CommentShipMapper;
import com.goout.ship.dao.LikeShipMapper;
import com.goout.ship.dao.ShipMapper;
import com.goout.ship.entity.LikeShip;
import com.goout.train.dao.CommentTrainMapper;
import com.goout.train.dao.LikeTrainMapper;
import com.goout.train.dao.TicketMapper;
import com.goout.train.model.response.LikeTrain;
import com.goout.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private LikeShipMapper likeShipMapper;
    @Autowired
    private CommentShipMapper commentShipMapper;
    @Autowired
    private ShipMapper shipMapper;

    @Autowired
    private LikeAirMapper likeAirMapper;
    @Autowired
    private CommentAirMapper commentAirMapper;
    @Autowired
    private AirMapper airMapper;

    @Autowired
    private LikeBusMapper likeBusMapper;
    @Autowired
    private CommentBusMapper commentBusMapper;
    @Autowired
    private BusMapper busMapper;

    @Autowired
    private LikeTrainMapper likeTrainMapper;
    @Autowired
    private CommentTrainMapper commentTrainMapper;
    @Autowired
    private TicketMapper trainMapper;

    @Override
    public List getCommontByUserId(Integer userId, String type) {
        switch (type){
            case "train":
                return commentTrainMapper.selectByUserId(userId);
            case "bus":
                return commentBusMapper.selectByUserId(userId);
            case "air":
                return commentAirMapper.selectByUserId(userId);
            case "ship":
                return commentShipMapper.selectByUserId(userId);
        }
        return null;
    }

    @Override
    public List getLikeByUserId(Integer userId, String type) {
        List list = new ArrayList();
        switch (type){
            case "train":
                List<LikeTrain> trains = likeTrainMapper.selectByUserId(userId);
                for(LikeTrain train : trains){
                    list.add(trainMapper.selectById(train.getTrainId()));
                }
                return list;
            case "bus":
                List<LikeBus> buses = likeBusMapper.selectByUserId(userId);
                for(LikeBus bus : buses){
                    list.add(busMapper.selectById(bus.getBusId()));
                }
                return list;
            case "air":
                List<LikeAir> airs = likeAirMapper.selectByUserId(userId);
                for(LikeAir air : airs){
                    list.add(airMapper.selectById(air.getAirId()));
                }
                return list;
            case "ship":
                List<LikeShip> ships = likeShipMapper.selectByUserId(userId);
                for(LikeShip ship : ships){
                    list.add(shipMapper.selectById(ship.getShipId()));
                }
                return list;
        }
        return null;
    }
}
