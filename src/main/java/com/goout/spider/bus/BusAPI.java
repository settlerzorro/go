package com.goout.spider.bus;

import com.goout.spider.bus.service.ISpiderBusService;
import com.goout.train.model.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * 同程旅行网爬取汽车票数据
 */
@RestController
@RequestMapping(value = "/spider/bus",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class BusAPI {
    @Autowired
    private ISpiderBusService spiderBusService;

    /**
     *
     * @param startTime startTime=2022-01-2
     * @param endTime endTime=2022-02-21
     * @return
     */
    @PostMapping(value = "insert")
    @ResponseBody
    public RestResponse REAL_INSERT(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime) {
        try {
            return RestResponse.succuess(spiderBusService.insert(startTime,endTime));
        }catch (Exception e){
            return RestResponse.fail("爬取数据失败");
        }

    }
}
