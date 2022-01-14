package com.goout.springsecurity.controller;

import com.goout.springsecurity.entity.SysUser;
import com.goout.springsecurity.entity.SysUserRole;
import com.goout.springsecurity.service.SysUserRoleService;
import com.goout.springsecurity.service.SysUserService;
import com.goout.train.model.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 */
@RestController
public class SessionController {
    private Logger logger= LoggerFactory.getLogger(SessionController.class);
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @PostMapping("/register")
    public RestResponse Register(@RequestBody SysUser sysUser){
        System.out.println("name ;"+sysUser.getUsername()+"  pwd:"+sysUser.getPassword());
        SysUser user=sysUserService.selectByName(sysUser.getUsername());
        if (user !=null){
            return RestResponse.fail("用户名称重复");
        }
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String Password = bcryptPasswordEncoder.encode(sysUser.getPassword());//加密
        sysUser.setPassword(Password);
        System.out.println("加密pwd;"+ sysUser.getPassword());
        Boolean in=sysUserService.insertUser(sysUser);
        SysUser user1=sysUserService.selectByName(sysUser.getUsername());
        //给用户默认user权限
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setUserId(user1.getId());
        sysUserRole.setRoleId(3);//ROLE_USER：3
        Boolean is=sysUserRoleService.insertUserRole(sysUserRole);
        return RestResponse.succuess(is);
    }

    @PostMapping("/modifyPass")
    public RestResponse modifyPass(@RequestParam("userName") String userName,@RequestBody Map map){
        String password = (String) map.get("password");
        String rePassword = (String) map.get("rePassword");
        System.out.println("userName ;"+userName + " 旧密码："+password +" 新密码："+rePassword);
        SysUser sysUser=sysUserService.selectByName(userName);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password,sysUser.getPassword())) {
            String encode = encoder.encode(rePassword);
            rePassword = encode;
            Integer i=sysUserService.modifyPass(userName,rePassword);
            if(i == 1){
                return RestResponse.succuess("修改成功");
            }
            return RestResponse.succuess("修改失败");
        }else {
            return RestResponse.succuess("修改失败");
        }
    }

//    @RequestMapping("/loginSuccess")
//    public RestResponse showHome(){
//        String name= SecurityContextHolder.getContext().getAuthentication().getName();
//        logger.info("当前登录用户:{}",name);
//        return RestResponse.succuess(true);
//    }
}
