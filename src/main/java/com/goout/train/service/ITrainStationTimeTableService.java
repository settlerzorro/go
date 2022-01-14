package com.goout.train.service;

import com.goout.train.model.request.GetTrainStationTimeTableRequest;
import com.goout.train.model.response.TrainStationTimeTableResult;

public interface ITrainStationTimeTableService {
    /**
     * 获取查询站点时刻表
     *
     * @param requestBody
     * @return
     */
    public TrainStationTimeTableResult getTrainStationTimeTable(GetTrainStationTimeTableRequest requestBody);
}
