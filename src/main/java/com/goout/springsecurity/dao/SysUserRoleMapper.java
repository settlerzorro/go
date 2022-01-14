package com.goout.springsecurity.dao;

import com.goout.springsecurity.entity.SysUserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE userId = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);

    @Insert("Insert into sys_user_role values(#{userId},#{roleId})")
    Boolean insertUserRole(SysUserRole sysUserRole);
}
