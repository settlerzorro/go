package com.goout.train.model.response;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.google.common.base.MoreObjects;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class Station {
    private String name;

    private String stationCode;

    private String air_stationCode;

    public String getAir_stationCode() {
        return air_stationCode;
    }

    public void setAir_stationCode(String air_stationCode) {
        this.air_stationCode = air_stationCode;
    }
    //    private String pingYin;
//
//    private String pingYinShort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

//    public String getPingYin() {
//        return pingYin;
//    }
//
//    public void setPingYin(String pingYin) {
//        this.pingYin = pingYin;
//    }
//
//    public String getPingYinShort() {
//        return pingYinShort;
//    }
//
//    public void setPingYinShort(String pingYinShort) {
//        this.pingYinShort = pingYinShort;
//    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("stationCode", stationCode)
//                .add("pingYin", pingYin)
//                .add("pingYinShort", pingYinShort)
                .toString();
    }
}
