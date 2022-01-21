package com.goout.spider.air.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goout.airplane.dao.AirMapper;
import com.goout.airplane.dao.CommentAirMapper;
import com.goout.airplane.entity.Air;
import com.goout.api.dao.StationMapper;
import com.goout.spider.air.service.ISpiderAirService;
import com.goout.train.model.response.Station;
import com.goout.train.utils.JsonUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("spiderAirService")
public class SpiderAirServiceImpl implements ISpiderAirService {
    private static final Logger logger = LoggerFactory.getLogger(SpiderAirServiceImpl.class);


    @Autowired
    private AirMapper airMapper;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private CommentAirMapper commentBusMapper;


    private static String getBusListUrlFmt = "https://www.ly.com/flights/api/getflightlist";

    private static String web = "https://www.ly.com/flights/itinerary/oneway/DLC-";

    @SneakyThrows
    public List<Air> insert(String startTime,String endTime)  {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");//如2016-08-10 20:40
        SimpleDateFormat simpleFormat2 = new SimpleDateFormat("yyyy-MM-dd");//如2016-08-10 20:40
        Date startDate =simpleFormat.parse(startTime);

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Long startLong = start.getTimeInMillis();

        Date endDate =simpleFormat.parse(endTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        Long endLong = end.getTimeInMillis();

        Long time = startLong;
        Long oneDay = 1000 * 60 * 60 * 24L;
        while (time <= endLong) {
            Date dat = new Date(time);
            List<Station> stas = stationMapper.selectStationAll();
            for(Station s:stas){
                if(s.getAir_stationCode() != null){
                    String[] code = s.getAir_stationCode().split(",");
                    for(String value : code){
                        String result = request(getBusListUrlFmt,"大连","DLC",s.getName(),value,simpleFormat2.format(time),Connection.Method.POST);
                        JSONObject js = JsonUtil.parseObject(result);
                        if(js != null && ((JSONObject)js.get("body")) != null && ((JSONObject)js.get("body")).size() >0){
                            Map body = (Map) js.get("body");
                            JSONArray flightInfoSimpleList = (JSONArray) body.get("FlightInfoSimpleList");
                            if(flightInfoSimpleList != null){
                                for(Object value2 : flightInfoSimpleList){
                                    JSONObject value3 = (JSONObject) value2;
                                    Air air = new Air();
                                    air.setOapName((String) value3.get("oapname"));
                                    air.setAapName((String) value3.get("aapname"));
                                    air.setFlyOffOnlyTime((String) value3.get("flyOffOnlyTime"));
                                    air.setArrivalOnlyTime((String) value3.get("arrivalOnlyTime"));
                                    air.setAep(new BigDecimal(value3.get("aep").toString()));
                                    air.setFromTime(simpleFormat2.format(time));

                                    long from = simpleFormat.parse((String) value3.get("flyOffTime")).getTime();
                                    long to = simpleFormat.parse((String) value3.get("arrivalTime")).getTime();
                                    int minutes = (int) ((to - from)/(1000 * 60));
                                    air.setUseTime(minutes);
                                    if(value3.get("si") != null){
                                        JSONArray si = (JSONArray) value3.get("si");
                                        StringBuilder sb = new StringBuilder();
                                        for(Object sis : si){
                                            JSONObject jingting = (JSONObject) sis;
                                            sb.append(jingting.get("son"));
                                            sb.append(" - ");
                                        }
                                        String stopName;
                                        if(!sb.toString().equals("")){
                                            stopName = StringUtils.substringBeforeLast(sb.toString()," - ");
                                            air.setSi(stopName);
                                        }
                                    }

                                    air.setBuyUrl(web + value + "?date=" + simpleFormat2.format(time)
                                            + "&from=" + java.net.URLEncoder.encode("大连","utf-8")
                                    +"&to=" + java.net.URLEncoder.encode(s.getName(),"utf-8")
                                    +"&fromairport=&toairport=&p=&childticket=0,0");
                                    try {
                                        airMapper.insertAir(air);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                }
                            }
                        }
                    }
                }
            }
            time += oneDay;
        }
        return null;
    }

    public static String request(String url,String goName,String goCode,String arrName,String arrCode,String time, Connection.Method method) {
        String result = null;
        Map map = new HashMap();
        Map map2 = new HashMap();

        map.put("Arrival", arrCode);
        map.put("ArrivalFilter", "");
        map.put("ArrivalName", arrName);

        map.put("Departure", goCode);
        map.put("DepartureDate", time);
        map.put("DepartureFilter", "");
        map.put("DepartureName", goName);

        map.put("GetType", "1");
        map.put("IsBaby", 0);
        map.put("QueryType", "1");
        map.put("flat", 1);
        map.put("fromairport", "");
        map.put("isFromKylin", 1);

        map2.put("dataflag","all");
        map.put("paging", map2);

        map.put("plat", 1);
        map.put("refid", "1035396140");
        map.put("toairport", "");
        try {
            Connection conn = Jsoup.connect(url)
                    .timeout(15000)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Accept", "application/json")
                    .header("Connection", "close")
                    .header("Cookie","17uCNRefId=RefId=1035396140&SEFrom=baidu&SEKeyWords=java%20%E6%B1%BD%E8%BD%A6%E7%A5%A8%E7%88%AC%E8%99%AB; TicketSEInfo=RefId=1035396140&SEFrom=baidu&SEKeyWords=java%20%E6%B1%BD%E8%BD%A6%E7%A5%A8%E7%88%AC%E8%99%AB; CNSEInfo=RefId=1035396140&tcbdkeyid=&SEFrom=baidu&SEKeyWords=java%20%E6%B1%BD%E8%BD%A6%E7%A5%A8%E7%88%AC%E8%99%AB&RefUrl=https%3A%2F%2Fwww.baidu.com%2Fs%3Fie%3Dutf-8%26f%3D8%26rsv_bp%3D1%26tn%3Dbaidu%26wd%3Djava%2520%25E6%25B1%25BD%25E8%25BD%25A6%25E7%25A5%25A8%25E7%2588%25AC%25E8%2599%25AB%26oq%3Djava%252520%2525E6%2525B1%2525BD%2525E8%2525BD%2525A6%2525E7%2525A5%2525A8%2525E6%25258A%252593%2525E5%25258F%252596%2525E8%2525BD%2525AF%2525E4%2525BB%2525B6%26rsv_pq%3D80ddd38a000a9438%26rsv_t%3D0a8324BuS%252BFeqZ1Wk2O6m3YWsZHsH7dxKtD5sRc2C3qGlTzSz3FEx1T8lX0%26rqlang%3Dcn%26rsv_dl%3Dtb%26rsv_enter%3D1%26rsv_btype%3Dt%26inputT%3D1088%26rsv_sug3%3D39%26rsv_sug1%3D13%26rsv_sug7%3D100%26rsv_sug2%3D0%26rsv_sug4%3D12435; __tctmu=144323752.0.0; __tctrack=0; longKey=164214557590952; __tctmz=144323752.1642145575512.1.1.utmccn=(organic)|utmcmd=organic|utmEsl=utf-8|utmcsr=baidu|utmctr=java%20%e6%b1%bd%e8%bd%a6%e7%a5%a8%e7%88%ac%e8%99%ab; _tcudid_v2=HSgHOMqW6JvTPGygdxJirMjbldIz57MBu9ayUwHrfdU; qdid=39264|1|6928722|0a6c16,39264|1|6928722|0a6c16; __ftoken=SgCxZeMwSdLtnKaiUmcz0SRYMDEJ21jt2xskshlKfdwNpPhdETUFf6AFgTZm8Y36WowtRJBa0UYl%2FjuV6VPK0w%3D%3D; __ftrace=ba00057d-bfff-472a-ac8f-a0b33c8c4003; _dx_captcha_cid=35385873; _dx_uzZo5y=0fb39627b3d15638437514f5cbe59e5cf806ee5c32c161d1cd14cbc3cbc4f2bd71399a1a; History=history={\"sCity\":[\"%E5%8C%97%E4%BA%AC\",\"%E5%A4%A7%E8%BF%9E\"],\"list\":[{\"sCity\":\"%E5%A4%A7%E8%BF%9E\",\"eCity\":\"%E5%8C%97%E4%BA%AC\",\"sDate\":\"2022-01-19\"}]}; ASP.NET_SessionId=mzht3mhhlbh1flieob1frczw; passport_login_state=pageurl=https%3a%2f%2fwww.ly.com%2f; _dx_app_bc4b3ca6ae27747981b43e9f4a6aa769=61e516edvOCDzXOCyI3OkBwMyuLPXT7mGVNesRn1; __tccgd=144323752.0; __tctma=144323752.164214557590952.1642145575512.1642403562491.1642409527020.4; __tctmd=144323752.737325; route=8b01b73ddb9a0b35bfc0aec7417be66a; tracerid=nologin-1642412843075; __tctmc=144323752.94055835; __tctmb=144323752.4116396659958601.1642412790644.1642412844153.7; __sd_captcha_id=c2e1b57f-7f95-448d-8a48-7735b3e6d759; _dx_captcha_vid=4CB0236911AA6444445E3E326ECF8D77B28B9BC2699CC3B6B96400458719EFECBB916639D0AFDA8322B8F23EAC9B7418C750ECB0A1EEA565D1B3ADB4A330F2861F4E47B5F6C17BAF2B1D3A5D20E62097")
                    .followRedirects(true)
                    .ignoreContentType(true);

            if (map != null) {
                conn.requestBody(JsonUtil.entity2Json(map));
            }
            Connection.Response response = conn.method(method).execute();

            int code = response.statusCode();
            if (code == 200 || code == 302) {
                result = response.body();
            } else {
                System.out.println("error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
