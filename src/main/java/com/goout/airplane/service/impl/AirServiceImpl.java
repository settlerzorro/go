package com.goout.airplane.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goout.airplane.dao.AirMapper;
import com.goout.airplane.dao.CommentAirMapper;
import com.goout.airplane.dao.LikeAirMapper;
import com.goout.airplane.entity.Air;
import com.goout.airplane.entity.CommentAir;
import com.goout.airplane.service.IAirService;
import com.goout.api.dao.StationMapper;
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
    private AirMapper airMapper;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private LikeAirMapper likeAirMapper;

    @Autowired
    private CommentAirMapper commentAirMapper;


    public List<Air> getAirList(Integer userId, Air air) {
        List<Air> list = airMapper.select(air);
        for(Air bu : list){
            bu.setCommentAairs(commentAirMapper.selectByAirId(bu.getId()));
            if(userId != null){
                bu.setLikeAirs(likeAirMapper.selectByAirIdAndUserId(bu.getId(),userId));
            }
        }
        return list;
    }


    @Override
    public boolean like(Integer userId,Integer airId) {
        return likeAirMapper.insertLikeAir(userId, airId);
    }

    @Override
    public boolean dislike(Integer id) {
        return likeAirMapper.deleteLikeAir(id);
    }

    @Override
    public boolean insertComment(JSONObject requestBody) {
        CommentAir ct = requestBody.toJavaObject(CommentAir.class);
        Date date = new Date();
        ct.setTime(date);
        return commentAirMapper.insert(ct);
    }

    @Override
    public boolean deleteComment(Integer id) {
        return commentAirMapper.deleteById(id);
    }

    @Override
    public boolean insertAir(Air air) {
        return airMapper.insertAir(air);
    }

    @Override
    public boolean deleteAir(Integer id) {
        return airMapper.deleteAir(id);
    }

    @Override
    public boolean updateAir(Air air) {
        return airMapper.updateAir(air);
    }

    @Override
    public List<Air> selectAll() {
        return airMapper.selectAll();
    }
}
