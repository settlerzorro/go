package com.goout.api.service.impl;

import com.goout.api.service.ICityStationService;
import com.goout.api.dao.StationMapper;
import com.goout.train.model.request.NoneRequest;
import com.goout.train.model.response.StationList;
import com.goout.train.model.response.StationResult;
import com.goout.train.model.response.TrainCodeResult;
import com.goout.train.utils.TrainHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Service("cityStationStationService")
public class CityStationServiceImpl implements ICityStationService {
    private static final Logger logger = LoggerFactory.getLogger(CityStationServiceImpl.class);

    @Autowired
    private StationMapper stationMapper;

    public StationResult getAllStation() {
        return new StationResult(new StationList(stationMapper.selectStationAll()));
    }

    @Override
    public StationResult getAllCity() {
        return new StationResult(new StationList(stationMapper.selectCityAll()));
    }

    public TrainCodeResult getAllTrainCode(NoneRequest requestBody) {
        // 优先从redis获取
        return new TrainCodeResult(getTrainCodeFromRedis());
    }

    private Map<String, Object> getTrainCodeFromRedis() {
        // 优先从redis中获取站点信息
        // String trainNoLinkStr = (String) redisUtils.get(RedisKeyConstant.REDIS_KEY_LOCAL_DATA_TRAIN_NO_LINK);
        Map<String, Object> trainNoLinkMap = null;
//        if (StringUtils.isNotBlank(trainNoLinkStr)) {
//            trainNoLinkMap = JSONObject.parseObject(trainNoLinkStr, Map.class);
//        }

        if (CollectionUtils.isEmpty(trainNoLinkMap)) {
            trainNoLinkMap = TrainHelper.getAllTrainNoListFromNet();
            // 设置到缓存
            //redisUtils.set(RedisKeyConstant.REDIS_KEY_LOCAL_DATA_TRAIN_NO_LINK, JSONObject.toJSONString(trainNoLinkMap), 1L, TimeUnit.DAYS);
        }
        return trainNoLinkMap;
    }


}
