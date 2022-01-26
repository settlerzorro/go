package com.goout.bus.dao;

import com.goout.airplane.entity.LikeAir;
import com.goout.bus.entity.LikeBus;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeBusMapper {
    @Select("SELECT * FROM like_bus WHERE busId = #{busId} and userId = #{userId}")
    List<LikeBus> selectByBusIdAndUserId(Integer busId, Integer userId);

    @Select("SELECT * FROM like_bus WHERE userId = #{userId}")
    List<LikeBus> selectByUserId(Integer userId);

    @Insert("Insert into like_bus (userId,busId) values(" +

            "#{userId}," +
            "#{busId})")
    Boolean insertLikeBus(Integer userId,Integer busId);

    @Delete("delete from like_bus where id=#{id}")
    Boolean deleteLikeBus(Integer id);
}
