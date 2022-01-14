package com.goout.springsecurity.service.impl;

import com.goout.springsecurity.dao.SysRoleMapper;
import com.goout.springsecurity.entity.SysRole;
import com.goout.springsecurity.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole selectById(Integer id) {
        return sysRoleMapper.selectById(id);
    }
}
