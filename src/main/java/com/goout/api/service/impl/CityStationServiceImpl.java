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
        return new TrainCodeResult(getTrainCode());
    }

    private Map<String, Object> getTrainCode() {
        Map<String, Object> trainNoLinkMap = null;

        if (CollectionUtils.isEmpty(trainNoLinkMap)) {
            trainNoLinkMap = TrainHelper.getAllTrainNoListFromNet();
        }
        return trainNoLinkMap;
    }


}
