package com.goout.bus.dao;

import com.goout.airplane.entity.Air;
import com.goout.bus.entity.Bus;
import com.goout.train.model.response.Train;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusMapper {
    @Select("<script> SELECT * FROM bus WHERE dptStation like concat('%','${dptStation}','%') <if test='arrStation != null'> and arrStation like concat('%','${arrStation}','%') </if> <if test='dptDate != null'>  and dptDate=#{dptDate} </if></script>")
    List<Bus> select(Bus bus);

    @Select("SELECT * FROM bus WHERE id=${id}")
    List<Bus> selectById(Integer id);

    @Select("SELECT * FROM bus")
    List<Bus> selectAll();


    @Insert("Insert into bus values(" +
            "#{id},#{dptStation}," +
            "#{arrStation},#{dptDate}," +
            "#{dptTime},#{coachType}," +
            "#{ticketLeft},#{ticketPrice},#{buyUrl},#{weather},#{scenicSpots})")
    Boolean insertBus(Bus ticket);

    @Delete("delete from bus where id=#{id}")
    Boolean deleteBus(Integer id);

    @Update("<script> " +
            "update bus " +
            "<set> " +
            "<if test='dptStation!=null'>  dptStation = #{dptStation} ,</if>" +
            "<if test='arrStation!=null'> arrStation = #{arrStation} , </if>" +
            "<if test='dptDate!=null'> dptDate = #{dptDate} ,</if>" +
            "<if test='dptTime!=null'> dptTime = #{dptTime}, </if>" +
            "<if test='coachType!=null'> coachType = #{coachType}, </if>" +
            "<if test='ticketLeft!=null'> ticketLeft = #{ticketLeft}, </if>" +
            "<if test='ticketPrice!=null'> ticketPrice = #{ticketPrice},</if> " +
            "<if test='buyUrl!=null'> buyUrl = #{buyUrl},</if> " +
            "<if test='weather!=null'> scenicSpots = #{weather},</if> " +
            "<if test='scenicSpots!=null'> scenicSpots = #{scenicSpots}</if> " +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    Boolean updateBus(Bus bus);
}
