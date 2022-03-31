package com.goout.comprehensive.dao;

import com.goout.comprehensive.entity.Comprehensive;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ComprehensiveMapper {

    @Select("SELECT * FROM comprehensive WHERE cityName=#{cityName} and date=#{date}")
    List<Comprehensive> selectByCityNameAndDate(String cityName,String date);

    @Select("<script> SELECT * FROM comprehensive WHERE " +
            "cityName=#{cityName} " +
            "</script>")
    List<Comprehensive> selectByCityName(String cityName);

    @Select("<script> SELECT * FROM comprehensive WHERE " +
            "date=#{date}" +
            "</script>")
    List<Comprehensive> selectByDate(String date);

    @Select("SELECT * FROM comprehensive")
    List<Comprehensive> selectAll();


    @Insert("insert into comprehensive values(" +
            "#{id},#{cityName},#{date}," +
            "#{weather},#{around}," +
            "#{hotel})")
    Boolean insert(Comprehensive ticket);

    @Delete("delete from comprehensive where id=#{id}")
    Boolean delete(Integer id);

    @Update("<script> " +
            "update comprehensive " +
            "<set> " +
            "<if test='cityName!=null'>  cityName = #{cityName} ,</if>" +
            "<if test='date!=null'> date = #{date} , </if>" +
            "<if test='weather!=null'> weather = #{weather} ,</if>" +
            "<if test='around!=null'> around = #{around}, </if>" +
            "<if test='hotel!=null'> hotel = #{hotel}</if>" +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    Boolean update(Comprehensive bus);
}
