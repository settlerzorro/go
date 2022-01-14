package com.goout.train.dao;

import com.goout.train.model.response.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    @Select("SELECT * FROM train WHERE fromStation like concat('%','${fromStation}','%') and toStation like concat('%','${toStation}','%') and fromDate=#{fromDate}")
    List<Ticket> select(Map map);

//    @Select("SELECT * FROM train WHERE userId = #{userId} and trainNo=#{ticket.trainNo} and fromStation=#{ticket.fromStation} and toStation=#{ticket.toStation}")
//    List<Ticket> select(String userId,Ticket ticket);

    @Insert("Insert into train values(" +
            "#{id},#{trainNo}," +
            "#{trainCode},#{trainType}," +
            "#{fromStation},#{toStation}," +
            "#{fromStationType},#{toStationType}," +
            "#{fromTime},#{toTime}," +
            "#{runTime},#{canBook}," +
            "#{swzNum},#{swzPrice}," +
            "#{ydzNum},#{ydzPrice}," +
            "#{edzNum},#{edzPrice}," +
            "#{gjrwNum},#{gjrwPrice}," +
            "#{rwNum},#{rwPrice}," +
            "#{dwNum},#{dwPrice}," +
            "#{ywNum},#{ywPrice}," +
            "#{rzNum},#{rzPrice}," +
            "#{yzNum},#{yzPrice}," +
            "#{wzNum},#{wzPrice}," +
            "#{qtNum},#{qtPrice}," +
            "#{trainLines},#{fromDate})")
    Boolean insertTrain(Ticket ticket);

    @Delete("delete from train where id=#{id}")
    Boolean deleteTrain(String id);
}
