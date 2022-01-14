package com.goout.springsecurity.service.impl;

import com.goout.springsecurity.dao.SysUserRoleMapper;
import com.goout.springsecurity.entity.SysUserRole;
import com.goout.springsecurity.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl  implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public List<SysUserRole> listByUserId(Integer userId) {
        return sysUserRoleMapper.listByUserId(userId);
    }

    @Override
    public Boolean insertUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insertUserRole(sysUserRole);
    }
}
