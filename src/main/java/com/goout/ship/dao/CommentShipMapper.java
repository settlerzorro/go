package com.goout.ship.dao;

import com.goout.ship.entity.CommentShip;
import com.goout.train.model.response.CommentTrain;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentShipMapper {
    @Select("SELECT * FROM comment_ship WHERE shipId=#{shipId}")
    List<CommentShip> selectByBusId(Integer  shipId);

    @Select("SELECT * FROM comment_ship WHERE userId=#{userId}")
    List<CommentShip> selectByUserId(Integer  userId);

    @Insert("insert into comment_ship values(#{id},#{userId},#{time},#{content},#{shipId})")
    Boolean insert(CommentShip commentShip);

    /**
     * 删除按钮需要前端判断删除者和评论者必须是同一人，不能删除其他人的。这里后端就懒得判断了
     * @param id
     * @return
     */
    @Delete("delete from comment_ship where id=#{id}")
    Boolean deleteById(Integer id);
}
