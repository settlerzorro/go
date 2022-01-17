package com.goout.train.dao;

import com.goout.train.model.response.Train;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    @Select("SELECT * FROM train WHERE fromStation like concat('%','${fromStation}','%') and toStation like concat('%','${toStation}','%') and fromDate=#{fromDate}")
    List<Train> select(Map map);

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
            "#{trainLines},#{fromDate},#{buyUrl})")
    Boolean insertTrain(Train ticket);


    @Delete("delete from train where id=#{id}")
    Boolean deleteTrain(Integer id);

    @Update("<script> " +
            "update train " +
            "<set> " +
            "<if test='trainNo!=null'>  trainNo = #{trainNo} ,</if>" +
            "<if test='trainCode!=null'> trainCode = #{trainCode} , </if>" +
            "<if test='trainType!=null'> trainType = #{trainType} ,</if>" +
            "<if test='fromStation!=null'> fromStation = #{fromStation}, </if>" +
            "<if test='toStation!=null'> toStation = #{toStation}, </if>" +
            "<if test='fromStationType!=null'> fromStationType = #{fromStationType}, </if>" +
            "<if test='toStationType!=null'> toStationType = #{toStationType}, </if>" +
            "<if test='fromTime!=null'> fromTime = #{fromTime}, </if>" +
            "<if test='toTime!=null'> toTime = #{toTime}, </if>" +
            "<if test='runTime!=null'> runTime = #{runTime}, </if>" +
            "<if test='canBook!=null'> canBook = #{canBook}, </if>" +
            "<if test='swzNum!=null'> swzNum = #{swzNum}, </if>" +
            "<if test='swzPrice!=null'> swzPrice = #{swzPrice}, </if>" +
            "<if test='ydzNum!=null'> ydzNum = #{ydzNum}, </if>" +
            "<if test='ydzPrice!=null'> ydzPrice = #{ydzPrice}, </if>" +
            "<if test='edzNum!=null'> edzNum = #{edzNum}, </if>" +
            "<if test='gjrwNum!=null'> gjrwNum = #{gjrwNum}, </if>" +
            "<if test='gjrwPrice!=null'> gjrwPrice = #{gjrwPrice}, </if>" +
            "<if test='rwNum!=null'> rwNum = #{rwNum}, </if>" +
            "<if test='rwPrice!=null'> rwPrice = #{rwPrice}, </if>" +
            "<if test='dwNum!=null'> dwNum = #{dwNum}, </if>" +
            "<if test='dwPrice!=null'> dwPrice = #{dwPrice}, </if>" +
            "<if test='ywNum!=null'> ywNum = #{ywNum}, </if>" +
            "<if test='yzPrice!=null'> yzPrice = #{yzPrice}, </if>" +
            "<if test='wzNum!=null'> wzNum = #{wzNum}, </if>" +
            "<if test='wzPrice!=null'> wzPrice = #{wzPrice}, </if>" +
            "<if test='qtNum!=null'> qtNum = #{qtNum}, </if>" +
            "<if test='qtPrice!=null'> qtPrice = #{qtPrice}, </if>" +
            "<if test='trainLines!=null'> trainLines = #{trainLines}, </if>" +
            "<if test='fromDate !=null'> fromDate = #{fromDate}</if> " +
            "<if test='buyUrl !=null'> buyUrl = #{buyUrl}</if> " +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    Boolean updateTrain(Train ticket);
}
