package com.goout.train.service;

import com.alibaba.fastjson.JSONObject;
import com.goout.train.model.request.GetTicketListRequest;
import com.goout.train.model.response.TicketListResult;
import com.goout.train.model.response.TrainLineResult;
import com.goout.train.model.request.GetTrainLineRequest;
import com.goout.train.model.response.*;

import java.util.List;

public interface ITrainTicketService {

    public List<Ticket> getTicketList(Integer userId, GetTicketListRequest requestBody);

    public TrainLineResult getTrainLine(GetTrainLineRequest requestBody);

    public boolean like(Integer userId,Integer trainId);

    public boolean dislike(Integer id);

    public boolean insertComment(JSONObject requestBody);

    public boolean deleteComment(Integer id);


}
