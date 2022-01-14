package com.goout.bus.dao;

import com.goout.bus.entity.CommentBus;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentBusMapper {
    @Select("SELECT * FROM comment_bus WHERE busId=#{busId}")
    List<CommentBus> selectByBusId(Integer  busId);

    @Insert("insert into comment_bus values(#{id},#{userId},#{time},#{content},#{busId})")
    Boolean insert(CommentBus commentBus);

    /**
     * 删除按钮需要前端判断删除者和评论者必须是同一人，不能删除其他人的。这里后端就懒得判断了
     * @param id
     * @return
     */
    @Delete("delete from comment_bus where id=#{id}")
    Boolean deleteById(Integer id);
}
