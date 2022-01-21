package com.goout.spider.air;

import com.goout.spider.air.service.ISpiderAirService;
import com.goout.train.model.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * 同程旅行网爬取飞机票数据
 */
@RestController
@RequestMapping(value = "/spider/air",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class AirAPI {
    @Autowired
    private ISpiderAirService spiderAirService;

    /**
     *
     * @param startTime startTime=2022-01-2 09:001
     * @param endTime endTime=2022-02-21 09:00
     * @return
     */
    @PostMapping(value = "insert")
    @ResponseBody
    public RestResponse REAL_INSERT(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime) {
        return RestResponse.succuess(spiderAirService.insert(startTime,endTime));
    }
}
