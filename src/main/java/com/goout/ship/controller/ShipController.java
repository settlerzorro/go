package com.goout.ship.controller;

import com.alibaba.fastjson.JSONObject;
import com.goout.bus.entity.Bus;
import com.goout.ship.entity.Ship;
import com.goout.ship.service.IShipService;
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
@RequestMapping(value = "/ship/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class ShipController {
    private static final Logger logger = LoggerFactory.getLogger(ShipController.class);

    @Autowired
    private IShipService shipService;



    @PostMapping(value = "getShipList")
    public RestResponse getShipListHandler(@RequestParam(value = "userId",required = false) Integer userId, HttpServletRequest request,@RequestBody Ship ship) {
        if(ship == null){
            return RestResponse.succuess(shipService.selectAll());
        }else {
            return RestResponse.succuess(shipService.getShipList(userId,ship));
        }
    }

    @GetMapping("/like")
    public RestResponse like(HttpServletRequest request,@RequestParam("userId") Integer userId,@RequestParam("shipId") Integer shipId) throws Exception {
        return RestResponse.succuess(shipService.like(userId,shipId));
    }

    @GetMapping("/dislike")
    public RestResponse dislike(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(shipService.dislike(id));
    }

    @PostMapping("/insertComment")
    public RestResponse insertComment(HttpServletRequest request,@RequestBody JSONObject requestBody) throws Exception {
        return RestResponse.succuess(shipService.insertComment(requestBody));
    }

    @DeleteMapping("/deleteComment")
    public RestResponse deleteComment(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(shipService.deleteComment(id));
    }


    @PostMapping("/insertShip")
    @PreAuthorize("hasRole('ROLE_SEA_ADMIN')")
    public RestResponse insertTrain(HttpServletRequest request,@RequestBody Ship ship) throws Exception {
        return RestResponse.succuess(shipService.insertShip(ship));
    }

    @DeleteMapping("/deleteShip")
    @PreAuthorize("hasRole('ROLE_SEA_ADMIN')")
    public RestResponse deleteTrain(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(shipService.deleteShip(id));
    }

    @PostMapping("/updateShip")
    @PreAuthorize("hasRole('ROLE_SEA_ADMIN')")
    public RestResponse updateTrain(HttpServletRequest request,@RequestBody Ship ship) throws Exception {
        return RestResponse.succuess(shipService.updateShip(ship));
    }
}
