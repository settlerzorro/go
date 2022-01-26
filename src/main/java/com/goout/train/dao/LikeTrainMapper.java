package com.goout.train.dao;

import com.goout.ship.entity.LikeShip;
import com.goout.train.model.response.LikeTrain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeTrainMapper {
    @Select("SELECT * FROM like_train WHERE trainId = #{trainId} and userId = #{userId}")
    List<LikeTrain> selectByTrainIdAndUserId(Integer trainId, Integer userId);

    @Select("SELECT * FROM like_train WHERE userId = #{userId}")
    List<LikeTrain> selectByUserId(Integer userId);

    @Insert("Insert into like_train (userId,trainId) values(" +

            "#{userId}," +
            "#{trainId})")
    Boolean insertLikeTrain(Integer userId,Integer trainId);

    @Delete("delete from like_train where id=#{id}")
    Boolean deleteLikeTrain(Integer id);
}
