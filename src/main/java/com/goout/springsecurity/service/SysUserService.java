package com.goout.springsecurity.service;


import com.goout.springsecurity.entity.SysUser;

public interface SysUserService {
     SysUser selectById(Integer id);

     SysUser selectByName(String name);

     Boolean insertUser(SysUser sysUser);

     Integer modifyPass(String name,String password);
}
