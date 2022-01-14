package com.goout.bus.dao;

import com.goout.bus.entity.Bus;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusMapper {
    @Select("SELECT * FROM bus WHERE dptStation like concat('%','${dptStation}','%') and arrStation like concat('%','${arrStation}','%') and dptDate=#{dptDate}")
    List<Bus> select(Bus bus);


    @Insert("Insert into bus values(" +
            "#{id},#{dptStation}," +
            "#{arrStation},#{dptDate}," +
            "#{dptTime},#{coachType}," +
            "#{ticketLeft},#{ticketPrice})")
    Boolean insertBus(Bus ticket);

    @Delete("delete from bus where id=#{id}")
    Boolean deleteBus(Integer id);

    @Update("<script> " +
            "update bus set " +
            "<if test='dptStation!=null'>  dptStation = #{dptStation} ,</if>" +
            "<if test='arrStation!=null'> arrStation = #{arrStation} , </if>" +
            "<if test='dptDate!=null'> dptDate = #{dptDate} ,</if>" +
            "<if test='dptTime!=null'> dptTime = #{dptTime}, </if>" +
            "<if test='coachType!=null'> coachType = #{coachType}, </if>" +
            "<if test='ticketLeft!=null'> ticketLeft = #{ticketLeft}, </if>" +
            "<if test='ticketPrice!=null'> ticketPrice = #{ticketPrice}</if>" +
            "where id=#{id}" +
            "</script>")
    Boolean updateBus(Bus bus);
}
