package com.goout.train.controller;

import com.alibaba.fastjson.JSONObject;
import com.goout.api.service.ICityStationService;
import com.goout.train.model.request.*;
import com.goout.train.model.response.*;
import com.goout.train.service.ITrainStationTimeTableService;
import com.goout.train.service.ITrainTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping(value = "/train/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class TrainController {
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    private ICityStationService trainStationService;
    @Autowired
    private ITrainTicketService trainTicketService;
    @Autowired
    private ITrainStationTimeTableService trainStationTimeTableService;


    @PostMapping(value = "getTicketList")
    public RestResponse getTicketListHandler(@RequestParam(value = "userId",required = false) Integer userId,HttpServletRequest request, @RequestBody JSONObject requestBody) {
        if(requestBody.isEmpty()){
            return RestResponse.succuess(trainTicketService.selectAll());
        }else{
            return RestResponse.succuess(trainTicketService.getTicketList(userId,requestBody.toJavaObject(GetTicketListRequest.class)));
        }
    }

    @GetMapping("/like")
    public RestResponse like(HttpServletRequest request,@RequestParam("userId") Integer userId,@RequestParam("trainId") Integer trainId) throws Exception {
        return RestResponse.succuess(trainTicketService.like(userId,trainId));
    }

    @GetMapping("/dislike")
    public RestResponse dislike(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(trainTicketService.dislike(id));
    }

    @PostMapping("/insertComment")
    public RestResponse insertComment(HttpServletRequest request,@RequestBody JSONObject requestBody) throws Exception {
        return RestResponse.succuess(trainTicketService.insertComment(requestBody));
    }

    @DeleteMapping("/deleteComment")
    public RestResponse deleteComment(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(trainTicketService.deleteComment(id));
    }

    @PostMapping("/insertTrain")
    @PreAuthorize("hasRole('ROLE_GROUND_ADMIN')")
    public RestResponse insertTrain(HttpServletRequest request,@RequestBody Train train) throws Exception {
        return RestResponse.succuess(trainTicketService.insertTrain(train));
    }

    @DeleteMapping("/deleteTrain")
    @PreAuthorize("hasRole('ROLE_GROUND_ADMIN')")
    public RestResponse deleteTrain(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(trainTicketService.deleteTrain(id));
    }

    @PostMapping("/updateTrain")
    @PreAuthorize("hasRole('ROLE_GROUND_ADMIN')")
    public RestResponse updateTrain(HttpServletRequest request,@RequestBody Train train) throws Exception {
        return RestResponse.succuess(trainTicketService.updateTrain(train));
    }

}
