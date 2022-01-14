package com.goout.train.service.impl;

import com.google.common.collect.Lists;
import com.goout.train.dao.StationMapper;
import com.goout.train.dao.TicketMapper;
import com.goout.train.model.request.NoneRequest;
import com.goout.train.model.request.SearchCityRequest;
import com.goout.train.model.response.Station;
import com.goout.train.model.response.StationList;
import com.goout.train.model.response.StationResult;
import com.goout.train.model.response.TrainCodeResult;
import com.goout.train.service.ITrainStationService;
import com.goout.train.utils.PreloadData;
import com.goout.train.utils.TrainHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service("trainStationService")
public class TrainStationServiceImpl implements ITrainStationService {
    private static final Logger logger = LoggerFactory.getLogger(TrainStationServiceImpl.class);

    @Autowired
    private StationMapper stationMapper;
//    @Autowired
//    private RedisUtils redisUtils;

    public StationResult getAllCity(NoneRequest requestBody) {
        // 从本地获取
        // return new StationResult(new StationList(getAllStation()));
        // 优先从redis获取
        return new StationResult(new StationList(getAllStatioonFromRedis()));
    }

    public StationResult getHotCity(NoneRequest requestBody) {
        // 从本地获取
        // return new StationResult(new StationList(PreloadData.getTrainHotCity()));
        // 优先从redis获取
        return new StationResult(new StationList(getHotStatioonFromRedis()));
    }

    /**
     * 获取 车次 - 列车号 数据
     *
     * @param requestBody
     * @return
     */
    public TrainCodeResult getAllTrainCode(NoneRequest requestBody) {
        // 优先从redis获取
        return new TrainCodeResult(getTrainCodeFromRedis());
    }

//    public StationResult searchCity(SearchCityRequest requestBody) {
//        requestBody.validate();
//
//        List<Station> ret = Lists.newArrayList();
//        String keyword = requestBody.getKeyword();
//        // 从本地获取
//        // List<Station> stations = getAllStation();
//        // 从redis获取
//        List<Station> stations = getAllStatioonFromRedis();
//        for (Station station : stations) {
//            boolean isMatching = station.getName().startsWith(keyword)
//                    || station.getPingYin().toLowerCase(Locale.ENGLISH).startsWith(keyword.toLowerCase(Locale.ENGLISH))
//                    || station.getPingYinShort().toLowerCase(Locale.ENGLISH).startsWith(keyword.toLowerCase(Locale.ENGLISH));
//            if (isMatching) {
//                ret.add(station);
//            }
//        }
//        return new StationResult(new StationList(ret));
//    }

    /**
     * 获取火车站点数据，先从本地获取，获取是失败在从12306获取
     *
     * @return 火车站点数据
     */
    private List<Station> getAllStation() {
        List<Station> stations = PreloadData.getTrainAllCity();
        if (CollectionUtils.isEmpty(stations)) {
            stations = TrainHelper.getTrainAllCityFromNet();
        }
        return stations;
    }

    /**
     * 获取火车站点数据，先从redis获取，获取是失败在从12306获取
     *
     * @return
     */
    private List<Station> getAllStatioonFromRedis() {
        List<Station> stations = stationMapper.selectAll();
        return stations;
    }

    /**
     * 获取热门火车站点数据，先从redis获取，获取是失败在从12306获取
     *
     * @return
     */
    private List<Station> getHotStatioonFromRedis() {
        // 优先从redis中获取站点信息
        //String HotStationStr = (String) redisUtils.get(RedisKeyConstant.REDIS_KEY_LOCAL_DATA_HOT_STATION);
        List<Station> hotStationsList = null;
//        if (StringUtils.isNotBlank(HotStationStr)) {
//            hotStationsList = JSONObject.parseArray(HotStationStr, Station.class);
//        }

        if (CollectionUtils.isEmpty(hotStationsList)) {
            hotStationsList = TrainHelper.getTrainHotStationFromNet();
            // 设置到缓存
            //redisUtils.set(RedisKeyConstant.REDIS_KEY_LOCAL_DATA_HOT_STATION, JSONObject.toJSONString(hotStationsList), 1L, TimeUnit.DAYS);
        }
        return hotStationsList;
    }

    /**
     * 获取 车次 - 列车号 数据，先从redis获取，获取是失败在从12306获取
     *
     * @return
     */
    private Map<String, Object> getTrainCodeFromRedis() {
        // 优先从redis中获取站点信息
       // String trainNoLinkStr = (String) redisUtils.get(RedisKeyConstant.REDIS_KEY_LOCAL_DATA_TRAIN_NO_LINK);
        Map<String, Object> trainNoLinkMap = null;
//        if (StringUtils.isNotBlank(trainNoLinkStr)) {
//            trainNoLinkMap = JSONObject.parseObject(trainNoLinkStr, Map.class);
//        }

        if (CollectionUtils.isEmpty(trainNoLinkMap)) {
            trainNoLinkMap = TrainHelper.getAllTrainNoListFromNet();
            // 设置到缓存
            //redisUtils.set(RedisKeyConstant.REDIS_KEY_LOCAL_DATA_TRAIN_NO_LINK, JSONObject.toJSONString(trainNoLinkMap), 1L, TimeUnit.DAYS);
        }
        return trainNoLinkMap;
    }
}
