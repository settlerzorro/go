package com.goout.springsecurity.securityConfig;

import com.goout.springsecurity.entity.SysRole;
import com.goout.springsecurity.entity.SysUser;
import com.goout.springsecurity.entity.SysUserRole;
import com.goout.springsecurity.service.SysRoleService;
import com.goout.springsecurity.service.SysUserRoleService;
import com.goout.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;
    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("userName:"+ username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = userService.selectByName(username);

        // 判断用户是否存在
        if(user == null) {
            throw new BadCredentialsException("用户名不存在");
        }

        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new SysUser(user.getId(),user.getUsername(), user.getPassword(),true, authorities);
    }
}
