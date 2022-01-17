package com.goout.airplane.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goout.airplane.dao.AirMapper;
import com.goout.airplane.dao.CommentAirMapper;
import com.goout.airplane.dao.LikeAirMapper;
import com.goout.airplane.entity.Air;
import com.goout.airplane.entity.CommentAir;
import com.goout.airplane.service.IAirService;
import com.goout.train.dao.StationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("airService")
public class AirServiceImpl implements IAirService {
    private static final Logger logger = LoggerFactory.getLogger(AirServiceImpl.class);

    @Autowired
    private AirMapper busMapper;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private LikeAirMapper likeBusMapper;

    @Autowired
    private CommentAirMapper commentBusMapper;


    private static String getBusListUrlFmt = "https://bus.ly.com/busresapi/schedule/getScheduleList?plateId=3";


    public List<Air> getAirList(Integer userId, Air air) {
        List<Air> list = busMapper.select(air);
        for(Air bu : list){
            bu.setCommentAairs(commentBusMapper.selectByAirId(bu.getId()));
            if(userId != null){
                bu.setLikeAirs(likeBusMapper.selectByAirIdAndUserId(bu.getId(),userId));
            }
        }
        return list;
    }


    @Override
    public boolean like(Integer userId,Integer airId) {
        return likeBusMapper.insertLikeAir(userId, airId);
    }

    @Override
    public boolean dislike(Integer id) {
        return likeBusMapper.deleteLikeAir(id);
    }

    @Override
    public boolean insertComment(JSONObject requestBody) {
        CommentAir ct = requestBody.toJavaObject(CommentAir.class);
        Date date = new Date();
        ct.setTime(date);
        return commentBusMapper.insert(ct);
    }

    @Override
    public boolean deleteComment(Integer id) {
        return commentBusMapper.deleteById(id);
    }

    @Override
    public boolean insertAir(Air air) {
        return busMapper.insertAir(air);
    }

    @Override
    public boolean deleteAir(Integer id) {
        return busMapper.deleteAir(id);
    }

    @Override
    public boolean updateAir(Air air) {
        return busMapper.updateAir(air);
    }
}
