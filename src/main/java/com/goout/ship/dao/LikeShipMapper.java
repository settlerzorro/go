package com.goout.ship.dao;

import com.goout.bus.entity.LikeBus;
import com.goout.ship.entity.LikeShip;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeShipMapper {
    @Select("SELECT * FROM like_ship WHERE shipId = #{shipId} and userId = #{userId}")
    List<LikeShip> selectByShipIdAndUserId(Integer shipId, Integer userId);

    @Select("SELECT * FROM like_ship WHERE userId = #{userId}")
    List<LikeShip> selectByUserId(Integer userId);

    @Insert("Insert into like_ship (userId,shipId) values(" +
            "#{userId}," +
            "#{shipId})")
    Boolean insertLikeShip(Integer userId, Integer shipId);

    @Delete("delete from like_ship where id=#{id}")
    Boolean deleteLikeShip(Integer id);
}
