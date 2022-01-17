package com.goout.ship.dao;

import com.goout.ship.entity.Ship;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShipMapper {
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
            ",#{buyUrl})")
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
            "<if test='buyUrl!=null'> buyUrl = #{buyUrl} </if> " +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    Boolean updateShip(Ship ship);
}
