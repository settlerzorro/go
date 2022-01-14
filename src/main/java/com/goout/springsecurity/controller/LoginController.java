package com.goout.springsecurity.controller;

import com.goout.springsecurity.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * 注解 @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
 */
@RestController
public class LoginController {
    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String showHome(){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登录用户:{}",name);
        return "home.html";
    }

    @PostMapping("/login")
    public String showLogin(@RequestBody SysUser sysUser) {
        System.out.println(sysUser);
        return "login";
    }

    @RequestMapping("/regis")
    public String register(){
        return "regis";
    }

    @RequestMapping("/modify")
    public Object modifyPass(HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        mav.addObject("name",name);
        mav.setViewName("modifyPass");
        return "mav";
    }
    @RequestMapping("/500")
    public String error() {
        return "500";
    }

    @RequestMapping("/400")
    public String error1() {
        return "400";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }
}
