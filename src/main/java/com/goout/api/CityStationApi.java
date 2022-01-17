package com.goout.api;

import com.goout.api.service.ICityStationService;
import com.goout.train.model.response.StationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/cityStationApi/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class CityStationApi {
    @Autowired
    private ICityStationService trainStationService;
    /**
     * 查询目的地城市，只包含省会
     * @param request
     * @return
     */
    @PostMapping(value = "getAllStation")
    @ResponseBody
    public StationResult getAllStationHandler(HttpServletRequest request) {
        return trainStationService.getAllStation();
    }

    /**
     * 查询目的地城市，只包含省会
     * @param request
     * @return
     */
    @PostMapping(value = "getAllCity")
    @ResponseBody
    public StationResult getAllCityHandler(HttpServletRequest request) {
        return trainStationService.getAllCity();
    }
}
