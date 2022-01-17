package com.goout.airplane.dao;

import com.goout.airplane.entity.LikeAir;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeAirMapper {
    @Select("SELECT * FROM like_air WHERE airId = #{airId} and userId = #{userId}")
    List<LikeAir> selectByAirIdAndUserId(Integer airId, Integer userId);

    @Insert("Insert into like_air (userId,airId) values(" +

            "#{userId}," +
            "#{airId})")
    Boolean insertLikeAir(Integer userId, Integer airId);

    @Delete("delete from like_air where id=#{id}")
    Boolean deleteLikeAir(Integer id);
}
