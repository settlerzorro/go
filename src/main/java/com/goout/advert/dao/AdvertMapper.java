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
            "#{localUrl},#{showAd})")
    Boolean insertAdvert(Advert air);

    @Delete("delete from advert where id=#{id}")
    Boolean deleteAdvert(Integer id);

    @Update("update advert set showAd = true where id=#{id}")
    Boolean show(@Param("id") String id);

    @Update("update advert set showAd = false where id=#{id}")
    Boolean hide(@Param("id") String id);

}
