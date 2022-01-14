package com.goout.train.dao;

import com.goout.train.model.response.CommentTrain;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentTrainMapper {
    @Select("SELECT * FROM comment_train WHERE trainId=#{trainId}")
    List<CommentTrain> selectByTrainId(Integer  trainId);

    @Insert("insert into comment_train values(#{id},#{userId},#{time},#{content},#{trainId})")
    Boolean insert(CommentTrain commentTrain);

    /**
     * 删除按钮需要前端判断删除者和评论者必须是同一人，不能删除其他人的。这里后端就懒得判断了
     * @param id
     * @return
     */
    @Delete("delete from comment_train where id=#{id}")
    Boolean deleteById(Integer id);
}
