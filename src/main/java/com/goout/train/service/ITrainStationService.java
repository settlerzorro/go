package com.goout.train.service;

import com.goout.train.model.request.NoneRequest;
import com.goout.train.model.request.SearchCityRequest;
import com.goout.train.model.response.StationResult;
import com.goout.train.model.response.TrainCodeResult;

public interface ITrainStationService {


    public StationResult getAllCity(NoneRequest requestBody);

    public StationResult getHotCity(NoneRequest requestBody);

    /**
     * 获取 车次 - 列车号 数据
     *
     * @param requestBody
     * @return
     */
    public TrainCodeResult getAllTrainCode(NoneRequest requestBody);

    //public StationResult searchCity(SearchCityRequest requestBody);

}
