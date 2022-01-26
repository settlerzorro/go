package com.goout.airplane.dao;

import com.goout.airplane.entity.CommentAir;
import com.goout.bus.entity.CommentBus;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentAirMapper {
    @Select("SELECT * FROM comment_air WHERE airId=#{airId}")
    List<CommentAir> selectByAirId(Integer  airId);

    @Select("SELECT * FROM comment_air WHERE userId=#{userId}")
    List<CommentAir> selectByUserId(Integer  userId);

    @Insert("insert into comment_air values(#{id},#{userId},#{time},#{content},#{airId})")
    Boolean insert(CommentAir commentAir);

    /**
     * 删除按钮需要前端判断删除者和评论者必须是同一人，不能删除其他人的。这里后端就懒得判断了
     * @param id
     * @return
     */
    @Delete("delete from comment_air where id=#{id}")
    Boolean deleteById(Integer id);
}
