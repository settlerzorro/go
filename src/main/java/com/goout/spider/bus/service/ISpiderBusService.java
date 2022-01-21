package com.goout.spider.bus.service;

import com.goout.bus.entity.Bus;

import java.text.ParseException;
import java.util.List;

public interface ISpiderBusService {

    public List<Bus> insert(String startTime, String endTime) throws ParseException;



}
