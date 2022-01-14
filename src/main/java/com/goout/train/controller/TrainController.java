package com.goout.train.controller;

import com.alibaba.fastjson.JSONObject;
import com.goout.train.model.request.*;
import com.goout.train.model.response.*;
import com.goout.train.model.request.*;
import com.goout.train.model.response.*;
import com.goout.train.service.ITrainStationService;
import com.goout.train.service.ITrainStationTimeTableService;
import com.goout.train.service.ITrainTicketService;
import com.goout.train.service.impl.REAL_INSERT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping(value = "/train/",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class TrainController {
    private static final Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    private ITrainStationService trainStationService;
    @Autowired
    private ITrainTicketService trainTicketService;
    @Autowired
    private ITrainStationTimeTableService trainStationTimeTableService;

    @Autowired
    private com.goout.train.service.impl.REAL_INSERT REAL_INSERT;

    /**
     * 查询目的地城市，只包含省会
     * @param request
     * @param requestBody
     * @return
     */
    @PostMapping(value = "getAllCity")
    @ResponseBody
    public StationResult getAllCityHandler(HttpServletRequest request, @RequestBody JSONObject requestBody) {
        return trainStationService.getAllCity(requestBody.toJavaObject(NoneRequest.class));
    }

//    @RequestMapping(value = "getHotCity")
//    @ResponseBody
//    public StationResult getHotCityHandler(HttpServletRequest request, @RequestBody JSONObject requestBody) {
//        return trainStationService.getHotCity(requestBody.toJavaObject(NoneRequest.class));
//    }

//    @RequestMapping(value = "getTrainCode")
//    @ResponseBody
//    public TrainCodeResult getTrainCodeHandler(HttpServletRequest request, @RequestBody JSONObject requestBody) {
//        return trainStationService.getAllTrainCode(requestBody.toJavaObject(NoneRequest.class));
//    }

//    @RequestMapping(value = "searchCity")
//    @ResponseBody
//    public StationResult searchCityHandler(HttpServletRequest request, @RequestBody JSONObject requestBody) {
//        return trainStationService.searchCity(requestBody.toJavaObject(SearchCityRequest.class));
//    }

//    @PostMapping(value = "getTicketList")
//    @ResponseBody
//    public TicketListResult getTicketListHandler(@RequestParam("userId") String userId,HttpServletRequest request, @RequestBody JSONObject requestBody) {
//        return trainTicketService.getTicketList(userId,requestBody.toJavaObject(GetTicketListRequest.class));
//    }

    @PostMapping(value = "getTicketList")
    public RestResponse getTicketListHandler(@RequestParam("userId") Integer userId,HttpServletRequest request, @RequestBody JSONObject requestBody) {
        List list = trainTicketService.getTicketList(userId,requestBody.toJavaObject(GetTicketListRequest.class));
        return RestResponse.succuess(list);
    }

//    @PostMapping(value = "getTrainLine")
//    @ResponseBody
//    public TrainLineResult getTrainLineHandler(HttpServletRequest request, @RequestBody JSONObject requestBody) {
//        return trainTicketService.getTrainLine(requestBody.toJavaObject(GetTrainLineRequest.class));
//    }

//    @RequestMapping(value = "getTrainStationTimeTable")
//    @ResponseBody
//    public TrainStationTimeTableResult getTrainStationTimeTable(HttpServletRequest request, @RequestBody JSONObject requestBody) {
//        return trainStationTimeTableService.getTrainStationTimeTable(requestBody.toJavaObject(GetTrainStationTimeTableRequest.class));
//    }

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


    @PostMapping(value = "REAL_INSERT")
    @ResponseBody
    public RestResponse REAL_INSERT(@RequestParam("userId") Integer userId,HttpServletRequest request, @RequestBody JSONObject requestBody) {
        return RestResponse.succuess(REAL_INSERT.getTicketList(userId,requestBody.toJavaObject(GetTicketListRequest.class)));
    }
}
