package com.goout.springsecurity.service;

import com.goout.springsecurity.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService {
    List<SysUserRole> listByUserId(Integer userId);

    Boolean insertUserRole(SysUserRole sysUserRole);
}
