package com.goout.api.service;

import com.goout.train.model.request.NoneRequest;
import com.goout.train.model.response.StationResult;
import com.goout.train.model.response.TrainCodeResult;

public interface ICityStationService {


    /**
     * 火车站用
     * @return
     */
    public StationResult getAllStation();

    /**
     * 飞机，汽车用
     * @return
     */
    public StationResult getAllCity();

    public TrainCodeResult getAllTrainCode(NoneRequest requestBody);



}
