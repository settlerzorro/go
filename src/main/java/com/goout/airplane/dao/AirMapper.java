package com.goout.airplane.dao;

import com.goout.airplane.entity.Air;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AirMapper {
    @Select("SELECT * FROM air WHERE oapName like concat('%','${oapName}','%') and aapName like concat('%','${aapName}','%') and fromTime=#{fromTime}")
    List<Air> select(Air air);


    @Insert("Insert into air values(" +
            "#{id},#{oapName}," +
            "#{aapName},#{flyOffOnlyTime}," +
            "#{arrivalOnlyTime},#{aep}," +
            "#{fromTime},#{buyUrl},#{si},#{useTime})")
    Boolean insertAir(Air air);

    @Delete("delete from air where id=#{id}")
    Boolean deleteAir(Integer id);

    @Update("<script> " +
            "update air " +
            "<set> " +
            "<if test='oapName!=null'>  oapName = #{oapName} ,</if>" +
            "<if test='aapName!=null'> aapName = #{aapName} , </if>" +
            "<if test='flyOffOnlyTime!=null'> flyOffOnlyTime = #{flyOffOnlyTime} ,</if>" +
            "<if test='arrivalOnlyTime!=null'> arrivalOnlyTime = #{arrivalOnlyTime}, </if>" +
            "<if test='aep!=null'> aep = #{aep}, </if>" +
            "<if test='fromTime!=null'> fromTime = #{fromTime}, </if>" +
            "<if test='buyUrl!=null'> buyUrl = #{buyUrl},</if> " +
            "<if test='useTime!=null'> useTime = #{useTime},</if> " +
            "<if test='si!=null'> si = #{si}</if> " +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    Boolean updateAir(Air bus);
}
