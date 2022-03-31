package com.goout.comprehensive.entity;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JSONType(naming = PropertyNamingStrategy.PascalCase)
public class Comprehensive implements Serializable {

    private Integer id;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 时间
     */
    private String date;

    /**
     * 天气
     */
    private String weather;

    /**
     * 周边
     */
    private String around;

    /**
     * 酒店
     */
    private String hotel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAround() {
        return around;
    }

    public void setAround(String around) {
        this.around = around;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Comprehensive{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", date='" + date + '\'' +
                ", weather='" + weather + '\'' +
                ", around='" + around + '\'' +
                ", hotel='" + hotel + '\'' +
                '}';
    }
}
