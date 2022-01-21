package com.goout.spider.train.service;

import com.goout.train.model.request.GetTicketListRequest;
import com.goout.train.model.response.TicketListResult;

import java.text.ParseException;

public interface ISpiderTrainService {

    public TicketListResult getTicketList(String startTime,String endTime,GetTicketListRequest requestBody) throws ParseException;



}
