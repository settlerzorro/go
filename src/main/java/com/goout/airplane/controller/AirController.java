package com.goout.airplane.controller;

import com.alibaba.fastjson.JSONObject;
import com.goout.airplane.entity.Air;
import com.goout.airplane.service.IAIR_REALService;
import com.goout.airplane.service.IAirService;
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
@RequestMapping(value = "/air/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class AirController {
    private static final Logger logger = LoggerFactory.getLogger(AirController.class);

    @Autowired
    private IAirService airService;

    @Autowired
    private IAIR_REALService REAL_INSERT;


    @PostMapping(value = "getAirList")
    public RestResponse getBusListHandler(@RequestParam(value = "userId",required = false) Integer userId, HttpServletRequest request, @RequestBody Air air) {
        List list = airService.getAirList(userId,air);
        return RestResponse.succuess(list);
    }

    @GetMapping("/like")
    public RestResponse like(HttpServletRequest request,@RequestParam("userId") Integer userId,@RequestParam("airId") Integer airId) throws Exception {
        return RestResponse.succuess(airService.like(userId,airId));
    }

    @GetMapping("/dislike")
    public RestResponse dislike(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(airService.dislike(id));
    }

    @PostMapping("/insertComment")
    public RestResponse insertComment(HttpServletRequest request,@RequestBody JSONObject requestBody) throws Exception {
        return RestResponse.succuess(airService.insertComment(requestBody));
    }

    @DeleteMapping("/deleteComment")
    public RestResponse deleteComment(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(airService.deleteComment(id));
    }


    @PostMapping(value = "REAL_INSERT")
    @ResponseBody
    public RestResponse REAL_INSERT(@RequestParam("userId") Integer userId,HttpServletRequest request, @RequestBody Air air) {
        return RestResponse.succuess(REAL_INSERT.insert(userId,air));
    }

    @PostMapping("/insertAir")
    @PreAuthorize("hasRole('ROLE_AIR_ADMIN')")
    public RestResponse insertTrain(HttpServletRequest request,@RequestBody Air air) throws Exception {
        return RestResponse.succuess(airService.insertAir(air));
    }

    @DeleteMapping("/deleteAir")
    @PreAuthorize("hasRole('ROLE_AIR_ADMIN')")
    public RestResponse deleteTrain(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(airService.deleteAir(id));
    }

    @PostMapping("/updateAir")
    @PreAuthorize("hasRole('ROLE_AIR_ADMIN')")
    public RestResponse updateTrain(HttpServletRequest request,@RequestBody Air air) throws Exception {
        return RestResponse.succuess(airService.updateAir(air));
    }
}
