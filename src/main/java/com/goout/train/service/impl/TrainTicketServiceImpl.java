package com.goout.train.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.goout.api.service.impl.CityStationServiceImpl;
import com.goout.train.dao.CommentTrainMapper;
import com.goout.train.dao.LikeTrainMapper;
import com.goout.api.dao.StationMapper;
import com.goout.train.dao.TicketMapper;
import com.goout.train.datamap.SeatTypeMap;
import com.goout.train.enums.train.PassengerType;
import com.goout.train.model.request.GetTicketListRequest;
import com.goout.train.model.response.*;
import com.goout.train.model.vo.TicketPrice;
import com.goout.train.datamap.TrainCodeTrainNoMap;
import com.goout.train.model.request.GetTrainLineRequest;
import com.goout.train.service.ITrainTicketService;
import com.goout.train.utils.TrainHelper;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service("trainTicketService")
public class TrainTicketServiceImpl implements ITrainTicketService {
    private static final Logger logger = LoggerFactory.getLogger(TrainTicketServiceImpl.class);

    @Autowired
    private CityStationServiceImpl trainStationService;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private LikeTrainMapper likeTrainMapper;

    @Autowired
    private CommentTrainMapper commentTrainMapper;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static String baseUrl = "https://kyfw.12306.cn";
    private static String publicName = "/otn";
    private static String leftTicketUrl = "leftTicket/queryA";
    private static String getTicketListUrlFmt = baseUrl + publicName +
            "/%s?" +
            "leftTicketDTO.train_date=%s&" +
            "leftTicketDTO.from_station=%s&" +
            "leftTicketDTO.to_station=%s&" +
            "purpose_codes=%s";


    private static String getTicketPriceUrlFmt = baseUrl + publicName  +
            "/leftTicket/queryTicketPrice?" +
            "train_no=%s&" +
            "from_station_no=%s&" +
            "to_station_no=%s&" +
            "seat_types=%s&" +
            "train_date=%s";

    private static String getTicketLineUrlFmt = baseUrl + publicName  +
            "/czxx/queryByTrainNo?" +
            "train_no=%s&" +
            "from_station_telecode=%s&" +
            "to_station_telecode=%s&" +
            "depart_date=%s";


    public List<Train> getTicketList(Integer userId, GetTicketListRequest requestBody) {
        requestBody.validate();
        Map map = new HashMap();
        map.put("fromStation",requestBody.getFromStation());
        map.put("toStation",requestBody.getToStation());
        map.put("fromDate",new DateTime(requestBody.getFromDate()).toString(DATE_FORMAT));
        List<Train> list = ticketMapper.select(map);
        for(Train ticket : list){
            ticket.setCommentTrains(commentTrainMapper.selectByTrainId(ticket.getId()));
            if(userId != null){
                ticket.setLikes(likeTrainMapper.selectByTrainIdAndUserId(ticket.getId(),userId));
            }

        }
        return list;
    }

    public TrainLineResult getTrainLine(GetTrainLineRequest requestBody) {
        requestBody.validate();
        String trainNo = TrainCodeTrainNoMap.getTrainNo(requestBody.getTrainCode());
        // ???????????????trainNo??????trainCode????????????
        if (StringUtils.isEmpty(trainNo)) {
            TrainCodeResult trainCodeResult = trainStationService.getAllTrainCode(null);
            trainNo = (String) trainCodeResult.getResult().get(requestBody.getTrainCode());
        }
        String fromDate = convertFromDate(requestBody.getFromDate());
        return new TrainLineResult(getTrainLineFrom12306(trainNo, fromDate, requestBody.getFromStationCode(), requestBody.getToStationCode()));
    }

    @Override
    public boolean  like(Integer userId,Integer trainId) {
        return likeTrainMapper.insertLikeTrain(userId,trainId);
    }

    @Override
    public boolean dislike(Integer id) {
        return likeTrainMapper.deleteLikeTrain(id);
    }

    @Override
    public boolean insertComment(JSONObject requestBody) {
        CommentTrain ct = requestBody.toJavaObject(CommentTrain.class);
        Date date = new Date();
        ct.setTime(date);
        return commentTrainMapper.insert(ct);
    }

    @Override
    public boolean deleteComment(Integer id) {
        return commentTrainMapper.deleteById(id);
    }

    @Override
    public boolean insertTrain(Train train) {
       return ticketMapper.insertTrain(train);
    }

    @Override
    public boolean deleteTrain(Integer id) {
        return ticketMapper.deleteTrain(id);
    }

    @Override
    public boolean updateTrain(Train train) {
        return ticketMapper.updateTrain(train);
    }

    @Override
    public List<Train> selectAll() {
        return ticketMapper.selectAll();
    }


    /**
     * ???12306??????????????????????????????????????????URL???????????????????????????????????????
     * @param requestBody ??????
     * @return ????????????
     */
    private List<Train> getTicketListFrom12306(Integer userId, GetTicketListRequest requestBody) {
        JSONObject ret12306 = TrainHelper.requestTo12306(getTicketListUrl(requestBody));
        if(ret12306 == null){
            return new ArrayList<>();
        }
        // ??????????????????????????????????????????????????? ?????????302??????????????? ?????????????????????(Temporarily Moved ) redirect
        if (ret12306.containsKey("c_url")) {
            logger.warn("????????????????????????????????????{}", ret12306);
            leftTicketUrl = ret12306.getString("c_url");
            ret12306 = TrainHelper.requestTo12306(getTicketListUrl(requestBody));
        }
        if(ret12306 == null){
            return new ArrayList<>();
        }

        JSONObject data = ret12306.getJSONObject("data");
        return buildTicketList(userId,requestBody, data);
    }



    /**
     * Date????????????yyyy-MM-dd?????????
     * @param fromDate Date????????????
     * @return yyyy-MM-dd?????????
     */
    private String convertFromDate(Date fromDate) {
        return new DateTime(fromDate).toString(DATE_FORMAT);
    }

    /**
     * 12306???????????????????????????????????????????????????
     * @param requestBody ??????
     * @param data 12306??????????????????
     * @return ?????????????????????
     */
    private List<Train> buildTicketList(Integer userId, GetTicketListRequest requestBody, JSONObject data) {
        List<Train> ret = Lists.newArrayList();

        //???????????????????????????
        JSONObject map = data.getJSONObject("map");
        String flag = data.getString("flag");
        JSONArray result = data.getJSONArray("result");
        if ("1".equals(flag)) {
            for (int i = 0; i < result.size(); i++) {
                String train = result.getString(i);
                List<String> trainItem = Splitter.on("|").splitToList(train);

                String secretStr = trainItem.get(0);
                if(StringUtils.isEmpty(secretStr)){
                    continue;//?????????????????????????????????????????????????????????????????????????????????????????????
                }
                String buttonTextInfo = trainItem.get(1); //?????????????????????

                String trainNo = trainItem.get(2); //?????????
                String trainCode = trainItem.get(3); //??????

                String startStationCode = trainItem.get(4); // ???????????????
                String endStationCode = trainItem.get(5); // ???????????????

                String fromStationCode = trainItem.get(6); //???????????????
                String toStationCode = trainItem.get(7); //???????????????
                String fromStationName = map.getString(fromStationCode); //?????????
                String toStationName = map.getString(toStationCode); //?????????

                String startTime = trainItem.get(8); //????????????
                String arriveTime = trainItem.get(9); //????????????
                String runTime = trainItem.get(10); //??????

                String canWebBuy = trainItem.get(11); //??????????????????Y ?????? N ?????? IS_TIME_NOT_BUY ?????????????????????,????????????/????????????
                String ypInfo = trainItem.get(12); //?

                String startTrainDate = trainItem.get(13); //???????????????????????????

                String trainSeatFeature = trainItem.get(14); //?
                String locationCode = trainItem.get(15); //?
                String fromStationNo = trainItem.get(16); //?????????????????????????????????????????????????????????01????????????????????????1???????????????
                String toStationNo = trainItem.get(17); //?????????????????????????????????????????????????????????
                String isSupportCard = trainItem.get(18); //???????????????????????????????????? 1 ?????? 0 ?????????
                String controlledTrainFlag = trainItem.get(19); //?

                String ggNum = StringUtils.isNotEmpty(trainItem.get(20)) ? trainItem.get(20) : "0"; //?
                String grNum = StringUtils.isNotEmpty(trainItem.get(21)) ? trainItem.get(21) : "0"; // ????????????
                String qtNum = StringUtils.isNotEmpty(trainItem.get(22)) ? trainItem.get(22) : "0"; // ??????
                String rwNum = StringUtils.isNotEmpty(trainItem.get(23)) ? trainItem.get(23) : "0"; // ??????/?????????
                String rzNum = StringUtils.isNotEmpty(trainItem.get(24)) ? trainItem.get(24) : "0"; // ??????
                String tzNum = StringUtils.isNotEmpty(trainItem.get(25)) ? trainItem.get(25) : "0"; //? ??????????
                String wzNum = StringUtils.isNotEmpty(trainItem.get(26)) ? trainItem.get(26) : "0"; // ??????
                String ybNum = StringUtils.isNotEmpty(trainItem.get(27)) ? trainItem.get(27) : "0"; //?
                String ywNum = StringUtils.isNotEmpty(trainItem.get(28)) ? trainItem.get(28) : "0"; // ??????/?????????
                String yzNum = StringUtils.isNotEmpty(trainItem.get(29)) ? trainItem.get(29) : "0"; // ??????
                String edzNum = StringUtils.isNotEmpty(trainItem.get(30)) ? trainItem.get(30) : "0"; // ?????????
                String ydzNum = StringUtils.isNotEmpty(trainItem.get(31)) ? trainItem.get(31) : "0"; // ?????????
                String swzNum = StringUtils.isNotEmpty(trainItem.get(32)) ? trainItem.get(32) : "0"; // ?????????/?????????
                String srrbNum = StringUtils.isNotEmpty(trainItem.get(33)) ? trainItem.get(33) : "0"; // ??????

                String ypEx = trainItem.get(34); //?
                String seatTypes = trainItem.get(35); //?????????????????????????seat_types??????
                String exchangeTrainFlag = trainItem.get(36); //?

                logger.info("????????????{} ?????????:{} ??????:{} ????????????:{} ????????????:{} ??????:{} ???????????????????????????:{} " +
                                "??????????????????:{} ?????????:{} ?????????:{} ????????????:{} ??????:{} ??????:{} ??????:{} ??????:{} ??????:{} ??????:{} ??????:{} ??????:{}",
                        trainCode, fromStationName, toStationName, startTime, arriveTime, runTime, startTrainDate,
                        swzNum, ydzNum, edzNum, grNum, rwNum, srrbNum, ywNum, rzNum, yzNum, wzNum, qtNum, buttonTextInfo);

                // ??????????????????--??????????????????
                TrainCodeTrainNoMap.put(trainCode, trainNo);
                String fromDate = convertFromDate(requestBody.getFromDate());

                TicketPrice ticketPrice = queryTicketPrice(trainNo, fromDate, fromStationNo, toStationNo, seatTypes);
//                TrainLine trainLine = getTrainLineFrom12306(trainNo,fromDate, fromStationCode, toStationCode);

                //TicketPrice ticketPrice = new TicketPrice();

                Train ticket = new Train();
                ticket.setTrainNo(trainNo);
                ticket.setTrainCode(trainCode);
                ticket.setTrainType(getTrainType(trainCode));
                ticket.setFromStation(fromStationName);
                ticket.setToStation(toStationName);
//                ticket.setFromStationType(getStationTypeName(fromStationNo, trainLine));
//                ticket.setToStationType(getStationTypeName(toStationNo, trainLine));
                ticket.setFromTime(startTime);
                ticket.setToTime(arriveTime);
                ticket.setRunTime(runTime);
                ticket.setCanBook(getCanBook(buttonTextInfo, canWebBuy));

                ticket.setSwzNum(getTicketNum(swzNum));
                ticket.setSwzPrice(ticketPrice.getSwzPrice());
                ticket.setYdzNum(getTicketNum(ydzNum));
                ticket.setYdzPrice(ticketPrice.getYdzPrice());
                ticket.setEdzNum(getTicketNum(edzNum));
                ticket.setEdzPrice(ticketPrice.getEdzPrice());
                ticket.setGjrwNum(getTicketNum(grNum));
                ticket.setGjrwPrice(ticketPrice.getGjrwPrice());
                ticket.setRwNum(getTicketNum(rwNum));
                ticket.setRwPrice(ticketPrice.getRwPrice());
                ticket.setDwNum(getTicketNum(srrbNum));
                ticket.setDwPrice(ticketPrice.getDwPrice());
                ticket.setYwNum(getTicketNum(ywNum));
                ticket.setYwPrice(ticketPrice.getYwPrice());
                ticket.setRzNum(getTicketNum(rzNum));
                ticket.setRzPrice(ticketPrice.getRzPrice());
                ticket.setYzNum(getTicketNum(yzNum));
                ticket.setYzPrice(ticketPrice.getYzPrice());
                ticket.setWzNum(getTicketNum(wzNum));
                ticket.setWzPrice(ticketPrice.getWzPrice());
                ticket.setQtNum(getTicketNum(qtNum));
                ticket.setQtPrice(ticketPrice.getQtPrice());
                Station from = stationMapper.selectStationByName(ticket.getFromStation());
                Station to = stationMapper.selectStationByName(ticket.getToStation());
                if(from != null && to != null){
                    String fromDateTime = new DateTime(requestBody.getFromDate()).toString(DATE_FORMAT);
                    TrainLine line =  getTrainLineFrom12306(ticket.getTrainNo(),fromDateTime,from.getStationCode(),to.getStationCode());
                    List<Stop> stops = line.getStops();
                    StringBuilder sb = new StringBuilder();
                    for(Stop stop : stops){
                        sb.append(stop.getStationName());
                        sb.append(" - ");
                    }
                    String stopName;
                    if(!sb.toString().equals("")){
                        stopName = StringUtils.substringBeforeLast(sb.toString()," - ");
                        ticket.setTrainLines(stopName);
                    }
                }

                ret.add(ticket);
            }
        }

        return ret;
    }

    /**
     * ????????????????????????
     * @param trainNo ????????? ????????????????????????
     * @param fromDate ????????????
     * @param fromStationCode ??????????????????
     * @param toStationCode ??????????????????
     * @return ????????????
     */
    private TrainLine getTrainLineFrom12306(String trainNo, String fromDate, String fromStationCode, String toStationCode) {
        String getTicketLineUrl = String.format(getTicketLineUrlFmt, trainNo, fromStationCode, toStationCode, fromDate);
        JSONObject ret12306 = TrainHelper.requestTo12306(getTicketLineUrl);
        if(ret12306 == null){
            return new TrainLine();
        }
        JSONArray stops = ret12306.getJSONObject("data").getJSONArray("data");

        TrainLine trainLine = new TrainLine();
        if (!CollectionUtils.isEmpty(stops)) {
            JSONObject stopInfoFirst = stops.getJSONObject(0);
            String startStationName = stopInfoFirst.getString("start_station_name"); //????????????
            String endStationName = stopInfoFirst.getString("end_station_name"); //????????????
            String stationTrainCode = stopInfoFirst.getString("station_train_code"); //?????????
            String trainClassName = stopInfoFirst.getString("train_class_name"); //??????????????? ?????????
            String serviceType = stopInfoFirst.getString("service_type"); //???????????? 0??????????????? ?????????????????????
            String serviceName = "0".equals(serviceType) ? "?????????" : "?????????";

            List<Stop> resultStops = Lists.newArrayList();
            for (int i = 0; i < stops.size(); i++) {
                Stop stop = new Stop();
                JSONObject stopInfo = stops.getJSONObject(i);
                Boolean isSearchStation = stopInfo.getBoolean("isEnabled"); //????????????????????????????????????????????? false?????? true???
                if(!isSearchStation){//false????????????????????????????????????
                    continue;
                }
                String startTime = stopInfo.getString("start_time"); //????????????????????? HH:mm???
                String arriveTime = stopInfo.getString("arrive_time"); //????????????????????? HH:mm ??????----???
                String stationName = stopInfo.getString("station_name"); //??????
                String stopoverTime = stopInfo.getString("stopover_time"); //???????????????????????? ?????????----
                String stationNo = stopInfo.getString("station_no"); //?????????01?????????
                stop.setStartTime(startTime);
                stop.setArriveTime(arriveTime);
                stop.setStationName(stationName);
                stop.setStopoverTime(stopoverTime);
                stop.setStationNo(stationNo);
                stop.setIsSearchStation(isSearchStation);
                resultStops.add(stop);
            }

            trainLine.setTrainCode(stationTrainCode);
            trainLine.setStartStationName(startStationName);
            trainLine.setEndStationName(endStationName);
            trainLine.setTrainClassName(trainClassName);
            trainLine.setServiceName(serviceName);
            trainLine.setStops(resultStops);
            return trainLine;
        }

        return trainLine;
    }

    /**
     * ?????????????????????
     * @param trainNo ????????? ????????????????????????
     * @param fromDate ????????????
     * @param fromStationNo ????????????
     * @param toStationNo ????????????
     * @param seatTypes ? ????????????????????????ypEx??????
     * @return ????????????
     */
    private TicketPrice queryTicketPrice(String trainNo, String fromDate, String fromStationNo, String toStationNo, String seatTypes) {
        String getTicketPriceUrl = String.format(getTicketPriceUrlFmt, trainNo, fromStationNo, toStationNo, seatTypes, fromDate);
        JSONObject ret12306 = TrainHelper.requestTo12306(getTicketPriceUrl);
        if(ret12306 == null){
            return new TicketPrice();
        }
        JSONObject data = ret12306.getJSONObject("data");

        Map<String, String> seatTypeMap = SeatTypeMap.getSeatTypeMap();
        BigDecimal swzPrice = processPrice(data.getString(seatTypeMap.get("swz")));
        BigDecimal ydzPrice = processPrice(data.getString(seatTypeMap.get("ydz")));
        BigDecimal edzPrice = processPrice(data.getString(seatTypeMap.get("edz")));
        BigDecimal grPrice = processPrice(data.getString(seatTypeMap.get("gr")));
        BigDecimal rwPrice = processPrice(data.getString(seatTypeMap.get("rw")));
        BigDecimal dwPrice = processPrice(data.getString(seatTypeMap.get("dw")));
        BigDecimal ywPrice = processPrice(data.getString(seatTypeMap.get("yw")));
        BigDecimal rzPrice = processPrice(data.getString(seatTypeMap.get("rz")));
        BigDecimal yzPrice = processPrice(data.getString(seatTypeMap.get("yz")));
        BigDecimal wzPrice = processPrice(data.getString(seatTypeMap.get("wz")));

        logger.info("?????????/???????????????:{} ???????????????:{} ???????????????:{} ??????????????????:{} ????????????:{} ????????????:{} ????????????:{} ????????????:{} ????????????:{} ????????????:{}",
                swzPrice, ydzPrice, edzPrice, grPrice, rwPrice, dwPrice, ywPrice, rzPrice, yzPrice, wzPrice);

        TicketPrice ticketPrice = new TicketPrice();
        ticketPrice.setSwzPrice(swzPrice);
        ticketPrice.setYdzPrice(ydzPrice);
        ticketPrice.setEdzPrice(edzPrice);
        ticketPrice.setGjrwPrice(grPrice);
        ticketPrice.setRwPrice(rwPrice);
        ticketPrice.setDwPrice(dwPrice);
        ticketPrice.setYwPrice(ywPrice);
        ticketPrice.setRzPrice(rzPrice);
        ticketPrice.setYzPrice(yzPrice);
        ticketPrice.setWzPrice(wzPrice);
        return ticketPrice;
    }

    /**
     * ?????????????????? ???|???|???
     * @param stationNo ????????????????????????????????????????????????01????????????????????????1???????????????
     * @param trainLine ??????????????????
     * @return ????????????
     */
    private String getStationTypeName(String stationNo, TrainLine trainLine) {
        String ret = "???";
        if (Integer.parseInt(stationNo) == 1) {
            ret = "???";
        } else if (Integer.parseInt(stationNo) == trainLine.getStops().size()) {
            ret = "???";
        } else {
            ret = "???";
        }
        return ret;
    }

    /**
     * ????????????????????
     * @param price ????????????
     * @return ?????????????????
     */
    private BigDecimal processPrice(String price) {
        BigDecimal ret = null;
        if (StringUtils.isNotEmpty(price) && price.startsWith("??")) {
            ret = new BigDecimal(price.substring(1));
        }
        return ret;
    }

    /**
     * ???????????????????????????
     * ???|???????????? --|??????????????????????????? ???????????????????????????
     * @param num 12306??????????????????
     * @return ?????????????????????
     */
    private String getTicketNum(String num) {
        return "???".equals(num) ? "0" : num;
    }

    /**
     * ???????????????????????????
     * @param buttonTextInfo ????????????
     * @param canWebBuy ??????????????????Y ?????? N ?????? IS_TIME_NOT_BUY ?????????????????????,????????????/????????????
     * @return ???????????????
     */
    private boolean getCanBook(String buttonTextInfo, String canWebBuy) {
        return "??????".endsWith(buttonTextInfo) && "Y".equals(canWebBuy);
    }

    /**
     * ??????????????????
     * G?????? C?????? D?????? Z?????? T?????? K?????? O??????
     * @param trainCode ????????????
     * @return ????????????
     */
    private String getTrainType(String trainCode) {
        String trainType = "O";
        if (Character.isLetter(trainCode.charAt(0))) {
            trainType = String.valueOf(trainCode.charAt(0));
        }
        return trainType;
    }

    /**
     * ?????????????????????url
     * @param requestBody ????????????
     * @return ??????URL
     */
    private String getTicketListUrl(GetTicketListRequest requestBody) {
        String fromDate = new DateTime(requestBody.getFromDate()).toString(DATE_FORMAT);
        String passengerType = PassengerType.ADULT.value();
        String value = String.format(getTicketListUrlFmt, leftTicketUrl, fromDate, requestBody.getFromStation(),
                requestBody.getToStation(), passengerType);
        return value;
    }

}
