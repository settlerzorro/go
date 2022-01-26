package com.goout.ship.dao;

import com.goout.bus.entity.Bus;
import com.goout.ship.entity.Ship;
import com.goout.train.model.response.Train;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShipMapper {
    @Select("<script>SELECT * FROM ship WHERE fromStation like concat('%','${fromStation}','%') <if test='toStation != null'> and toStation like concat('%','${toStation}','%') </if></script>")
    List<Ship> select(Ship ship);

    @Select("SELECT * FROM ship WHERE id=${id}")
    List<Ship> selectById(Integer id);

    @Select("SELECT * FROM ship")
    List<Ship> selectAll();


    @Insert("Insert into ship values(" +
            "#{id},#{shipName}," +
            "#{startTime},#{fromStation}," +
            "#{endTime},#{toStation}," +
            "#{tdPrice},#{ydPrice}" +
            ",#{edAprice}" +
            ",#{edBprice}" +
            ",#{sdAPrice}" +
            ",#{sdBPrice}" +
            ",#{sdPrice}" +
            ",#{sxPrice}" +
            ",#{buyUrl}" +
            ",#{weather},#{scenicSpots})")
    Boolean insertShip(Ship ship);

    @Delete("delete from ship where id=#{id}")
    Boolean deleteShip(Integer id);

    @Update("<script> " +
            "update ship " +
            "<set>" +
            "<if test='shipName!=null'>  shipName = #{shipName} ,</if>" +
            "<if test='startTime!=null'> startTime = #{startTime} , </if>" +
            "<if test='fromStation!=null'> fromStation = #{fromStation} ,</if>" +
            "<if test='endTime!=null'> endTime = #{endTime}, </if>" +
            "<if test='toStation!=null'> toStation = #{toStation}, </if>" +
            "<if test='tdPrice!=null'> tdPrice = #{tdPrice}, </if>" +
            "<if test='ydPrice!=null'> ydPrice = #{ydPrice}, </if>" +
            "<if test='edAprice!=null'> edAprice = #{edAprice}, </if>" +
            "<if test='edBprice!=null'> edBprice = #{edBprice}, </if>" +
            "<if test='sdAPrice!=null'> sdAPrice = #{sdAPrice}, </if>" +
            "<if test='sdBPrice!=null'> sdBPrice = #{sdBPrice}, </if>" +
            "<if test='sdPrice!=null'> sdPrice = #{sdPrice}, </if>" +
            "<if test='sxPrice!=null'> sxPrice = #{sxPrice}, </if> " +
            "<if test='buyUrl!=null'> buyUrl = #{buyUrl} ,</if> " +
            "<if test='weather!=null'> scenicSpots = #{weather},</if> " +
            "<if test='scenicSpots!=null'> scenicSpots = #{scenicSpots}</if> " +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    Boolean updateShip(Ship ship);
}
