package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class TrainStationTimeTableResult extends BaseResult {
    private List<TrainStationTimeTable> result;

    public TrainStationTimeTableResult() {
    }

    public TrainStationTimeTableResult(List<TrainStationTimeTable> result) {
        this.result = result;
    }

    public List<TrainStationTimeTable> getResult() {
        return result;
    }

    public void setResult(List<TrainStationTimeTable> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("result", result)
                .toString();
    }
}
