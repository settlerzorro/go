package com.goout.spider.train;

import com.goout.spider.train.service.impl.SpiderTrainServiceImpl;
import com.goout.train.model.request.GetTicketListRequest;
import com.goout.train.model.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 爬取12306接口
 */
@RestController
@RequestMapping(value = "/spider/train/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class TrainAPI {

    @Autowired
    private SpiderTrainServiceImpl REAL_INSERT;

    @PostMapping(value = "insert")
    public RestResponse REAL_INSERT( HttpServletRequest request,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime) {
        try {
            return RestResponse.succuess(REAL_INSERT.getTicketList(startTime,endTime,new GetTicketListRequest()));
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.fail("爬取失败");
        }

    }
}
