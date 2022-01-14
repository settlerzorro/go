package com.goout.springsecurity.controller;

import com.goout.springsecurity.entity.SysUser;
import com.goout.springsecurity.entity.SysUserRole;
import com.goout.springsecurity.service.SysUserRoleService;
import com.goout.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
public class RegController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @PostMapping("/register")
    public String Register(@RequestBody SysUser sysUser){
        System.out.println("name ;"+sysUser.getName()+"  pwd:"+sysUser.getPassword());
        SysUser user=sysUserService.selectByName(sysUser.getName());
        if (user !=null){
            System.out.println("用户名称重复");
            return "用户名称重复";
        }
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String Password = bcryptPasswordEncoder.encode(sysUser.getPassword());//加密
        sysUser.setPassword(Password);
        System.out.println("加密pwd;"+ sysUser.getPassword());
        Boolean in=sysUserService.insertUser(sysUser);
        SysUser user1=sysUserService.selectByName(sysUser.getName());
        //给用户默认user权限
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setUserId(user1.getId());
        sysUserRole.setRoleId(3);//ROLE_USER：3
        Boolean is=sysUserRoleService.insertUserRole(sysUserRole);
        return "login";
    }

    @RequestMapping("/modifyPass")
    public String modifyPass(String name,String password,String rePassword){
        System.out.println("name ;"+name + " 旧密码："+password +" 新密码："+rePassword);
        SysUser sysUser=sysUserService.selectByName(name);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password,sysUser.getPassword())) {
            String encode = encoder.encode(rePassword);
            rePassword = encode;
            Integer i=sysUserService.modifyPass(name,rePassword);
            System.out.println("成功！"+i);
            return "home";
        }else {
            return "home";
        }
    }
}
