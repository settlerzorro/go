package com.goout.springsecurity.service.impl;

import com.goout.springsecurity.dao.SysUserMapper;
import com.goout.springsecurity.entity.SysUser;
import com.goout.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "SysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser selectById(Integer id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser selectByName(String name) {
        return sysUserMapper.selectByName(name);
    }

    @Override
    public Boolean insertUser(SysUser sysUser) {
        return sysUserMapper.insertUser(sysUser);
    }

    @Override
    public Integer modifyPass(String name, String password) {
        return sysUserMapper.modifyPass(name,password);
    }
}
