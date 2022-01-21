package com.goout.bus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goout.airplane.entity.Air;
import com.goout.bus.dao.BusMapper;
import com.goout.bus.dao.CommentBusMapper;
import com.goout.bus.dao.LikeBusMapper;
import com.goout.bus.entity.Bus;
import com.goout.bus.entity.CommentBus;
import com.goout.bus.service.IBusService;
import com.goout.api.dao.StationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("busService")
public class BusServiceImpl implements IBusService {
    private static final Logger logger = LoggerFactory.getLogger(BusServiceImpl.class);

    @Autowired
    private BusMapper busMapper;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private LikeBusMapper likeBusMapper;

    @Autowired
    private CommentBusMapper commentBusMapper;


    private static String getBusListUrlFmt = "https://bus.ly.com/busresapi/schedule/getScheduleList?plateId=3";


    public List<Bus> getBusList(Integer userId, Bus bus) {
        List<Bus> list = busMapper.select(bus);
        for(Bus bu : list){
            bu.setCommentBuses(commentBusMapper.selectByBusId(bu.getId()));
            if(userId != null){
                bu.setLikeBuses(likeBusMapper.selectByBusIdAndUserId(bu.getId(),userId));
            }
        }
        return list;
    }


    @Override
    public boolean like(Integer userId,Integer busId) {
        return likeBusMapper.insertLikeBus(userId,busId);
    }

    @Override
    public boolean dislike(Integer id) {
        return likeBusMapper.deleteLikeBus(id);
    }

    @Override
    public boolean insertComment(JSONObject requestBody) {
        CommentBus ct = requestBody.toJavaObject(CommentBus.class);
        Date date = new Date();
        ct.setTime(date);
        return commentBusMapper.insert(ct);
    }

    @Override
    public boolean deleteComment(Integer id) {
        return commentBusMapper.deleteById(id);
    }

    @Override
    public boolean insertBus(Bus bus) {
        return busMapper.insertBus(bus);
    }

    @Override
    public boolean deleteBus(Integer id) {
        return busMapper.deleteBus(id);
    }

    @Override
    public boolean updateBus(Bus bus) {
        return busMapper.updateBus(bus);
    }

    @Override
    public List<Bus> selectAll() {
        return busMapper.selectAll();
    }
}
