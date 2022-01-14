package com.goout.springsecurity.dao;

import com.goout.springsecurity.entity.SysUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    SysUser selectByName(String username);

    @Insert("insert into sys_user(username,password) values(#{username},#{password})")
    Boolean insertUser(SysUser sysUser);

    @Update("update sys_user set password = #{password} where username=#{username}")
    Integer modifyPass(@Param("username") String username,@Param("password") String password);
}
