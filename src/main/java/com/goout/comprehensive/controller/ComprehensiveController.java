package com.goout.comprehensive.controller;

import com.alibaba.fastjson.JSONObject;
import com.goout.bus.entity.Bus;
import com.goout.comprehensive.entity.Comprehensive;
import com.goout.comprehensive.service.IComprehensiveService;
import com.goout.train.model.response.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/comprehensive/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class ComprehensiveController {
    private static final Logger logger = LoggerFactory.getLogger(ComprehensiveController.class);

    @Autowired
    private IComprehensiveService comprehensiveService;



    @GetMapping(value = "getComprehensiveByFilter")
    public RestResponse getComprehensiveByFilter(@RequestParam("cityName") String cityName,@RequestParam("date") String date, HttpServletRequest request) {
        if(StringUtils.isEmpty(cityName) && StringUtils.isEmpty(date)){
            return RestResponse.succuess(comprehensiveService.selectAll());
        }
        if(StringUtils.isNotEmpty(cityName) && StringUtils.isNotEmpty(date)){
            return RestResponse.succuess(comprehensiveService.getComprehensive(cityName,date));
        }

        if(StringUtils.isNotEmpty(cityName)){
            return RestResponse.succuess(comprehensiveService.selectByCityName(cityName));
        }
        return RestResponse.succuess(comprehensiveService.selectByDate(date));
    }

    @PostMapping("/insertComprehensive")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public RestResponse insertComprehensive(HttpServletRequest request,@RequestBody JSONObject requestBody) throws Exception {
        return RestResponse.succuess(comprehensiveService.insertComprehensive(requestBody));
    }

    @DeleteMapping("/deleteComprehensive")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public RestResponse deleteComprehensive(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(comprehensiveService.deleteComprehensive(id));
    }


    @PostMapping("/updateComprehensive")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public RestResponse updateComprehensive(HttpServletRequest request,@RequestBody Comprehensive bus) throws Exception {
        return RestResponse.succuess(comprehensiveService.updateComprehensive(bus));
    }
}
