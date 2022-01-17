package com.goout.advert.dao;

import com.goout.advert.entity.Advert;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdvertMapper {
    @Select("SELECT * FROM advert ")
    List<Advert> select();


    @Insert("Insert into advert values(" +
            "#{id},#{name}," +
            "#{localUrl})")
    Boolean insertAdvert(Advert air);

    @Delete("delete from advert where id=#{id}")
    Boolean deleteAdvert(Integer id);

}
