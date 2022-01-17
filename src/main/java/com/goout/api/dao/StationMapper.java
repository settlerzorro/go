package com.goout.api.dao;

import com.goout.train.model.response.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StationMapper {
    @Select("SELECT * FROM station")
    List<Station> selectStationAll();

    @Select("SELECT * FROM station where name=#{name}")
    Station selectStationByName(String name);

    @Select("SELECT * FROM city")
    List<Station> selectCityAll();

    @Select("SELECT * FROM city where name=#{name}")
    Station selectCityByName(String name);
}
