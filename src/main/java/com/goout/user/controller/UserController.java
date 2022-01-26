package com.goout.user.controller;

import com.goout.train.model.response.RestResponse;
import com.goout.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/userInfo/",produces = "application/json;charset=UTF-8")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IUserService userService;


    @GetMapping("/getCommont")
    public RestResponse getCommont(@RequestParam(value = "userId",required = true) Integer userId, @RequestParam(value = "type",required = true) String type, HttpServletRequest request) {
        return RestResponse.succuess(userService.getCommontByUserId(userId,type));
    }

    @GetMapping("/getLike")
    public RestResponse getLike(@RequestParam(value = "userId",required = true) Integer userId, @RequestParam(value = "type",required = true) String type, HttpServletRequest request) {
        return RestResponse.succuess(userService.getLikeByUserId(userId,type));
    }
}
