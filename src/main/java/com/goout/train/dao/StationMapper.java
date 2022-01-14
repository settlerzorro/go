package com.goout.train.dao;

import com.goout.train.model.response.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StationMapper {
    @Select("SELECT * FROM station")
    List<Station> selectAll();

    @Select("SELECT * FROM station where name=#{name}")
    Station selectByName(String name);
}
