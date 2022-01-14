package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class StationList {
    protected List<Station> stations;

    public StationList() {
    }

    public StationList(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("stations", stations)
                .toString();
    }
}
