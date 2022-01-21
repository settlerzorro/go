package com.goout.bus.controller;

import com.alibaba.fastjson.JSONObject;
import com.goout.bus.entity.Bus;
import com.goout.bus.service.IBusService;
import com.goout.train.model.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/bus/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class BusController {
    private static final Logger logger = LoggerFactory.getLogger(BusController.class);

    @Autowired
    private IBusService busService;



    @PostMapping(value = "getBusList")
    public RestResponse getBusListHandler(@RequestParam(value = "userId",required = false) Integer userId, HttpServletRequest request, @RequestBody Bus bus) {
        if(bus == null){
            return RestResponse.succuess(busService.selectAll());
        }else{
            return RestResponse.succuess(busService.getBusList(userId,bus));
        }
    }

    @GetMapping("/like")
    public RestResponse like(HttpServletRequest request,@RequestParam("userId") Integer userId,@RequestParam("busId") Integer busId) throws Exception {
        return RestResponse.succuess(busService.like(userId,busId));
    }

    @GetMapping("/dislike")
    public RestResponse dislike(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(busService.dislike(id));
    }

    @PostMapping("/insertComment")
    public RestResponse insertComment(HttpServletRequest request,@RequestBody JSONObject requestBody) throws Exception {
        return RestResponse.succuess(busService.insertComment(requestBody));
    }

    @DeleteMapping("/deleteComment")
    public RestResponse deleteComment(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(busService.deleteComment(id));
    }


    @PostMapping("/insertBus")
    @PreAuthorize("hasRole('ROLE_GROUND_ADMIN')")
    public RestResponse insertTrain(HttpServletRequest request,@RequestBody Bus bus) throws Exception {
        return RestResponse.succuess(busService.insertBus(bus));
    }

    @DeleteMapping("/deleteBus")
    @PreAuthorize("hasRole('ROLE_GROUND_ADMIN')")
    public RestResponse deleteTrain(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(busService.deleteBus(id));
    }

    @PostMapping("/updateBus")
    @PreAuthorize("hasRole('ROLE_GROUND_ADMIN')")
    public RestResponse updateTrain(HttpServletRequest request,@RequestBody Bus bus) throws Exception {
        return RestResponse.succuess(busService.updateBus(bus));
    }
}
