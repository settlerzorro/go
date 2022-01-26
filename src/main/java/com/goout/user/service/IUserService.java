package com.goout.user.service;

import com.goout.bus.entity.CommentBus;

import java.util.List;

public interface IUserService {

    public List getCommontByUserId(Integer userId,String type);

    public List getLikeByUserId(Integer userId,String type);





}
