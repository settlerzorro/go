package com.goout.bus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goout.bus.dao.BusMapper;
import com.goout.bus.dao.CommentBusMapper;
import com.goout.bus.dao.LikeBusMapper;
import com.goout.bus.entity.Bus;
import com.goout.bus.service.IBUS_REALService;
import com.goout.api.dao.StationMapper;
import com.goout.train.model.response.*;
import com.goout.train.utils.JsonUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

@Service("BUS_REAL_INSERT")
public class REAL_INSERT  implements IBUS_REALService {
    private static final Logger logger = LoggerFactory.getLogger(REAL_INSERT.class);


    @Autowired
    private BusMapper busMapper;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private LikeBusMapper likeBusMapper;

    @Autowired
    private CommentBusMapper commentBusMapper;


    private static String getBusListUrlFmt = "https://bus.ly.com/busresapi/schedule/getScheduleList?plateId=3";

    private static String web = "https://bus.ly.com/#/list?startname=";

    public List<Bus> insert(Integer userId, Bus bus)  {
        for(int i=16;i<=30;i++){
            List<Station> stas = stationMapper.selectStationAll();
            for(Station s:stas){
                String result = request(getBusListUrlFmt,"大连",s.getName(),"2022-01-"+i,Connection.Method.POST);
                JSONObject js = JsonUtil.parseObject(result);
                if(js != null){
                    Map header = (Map) js.get("header");
                    boolean success = (boolean) header.get("isSuccess");
                    if(success){
                        Bus bus2 = new Bus();
                        Map body = (Map) js.get("body");
                        List<Map> schedule = (List) body.get("schedule");
                        for(Map sc : schedule){
                            bus2.setDptStation((String) sc.get("dptStation"));
                            bus2.setArrStation((String) sc.get("arrStation"));
                            bus2.setDptDate((String) sc.get("dptDate"));
                            bus2.setDptTime((String) sc.get("dptTime"));
                            bus2.setCoachType((String) sc.get("coachType"));
                            bus2.setTicketLeft((String) sc.get("ticketLeft"));
                            bus2.setTicketPrice(new BigDecimal(sc.get("ticketPrice").toString()));
                            try {
                                bus2.setBuyUrl(web + java.net.URLEncoder.encode("大连","utf-8")
                                +"&arrivename="+java.net.URLEncoder.encode(s.getName(),"utf-8")
                                +"&depCId="+sc.get("depId")
                                +"&desCId="+sc.get("desId")
                                +"&startdatetime="+"2022-01-"+i
                                +"&startStation=&arriveStation=");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            busMapper.insertBus(bus2);
                        }
                    }
                }
                System.out.println(result);
            }

        }
        return null;
    }

    public static String request(String url,String go,String arr,String time, Connection.Method method) {
        String result = null;
        Map map = new HashMap();
        map.put("arrivalStation", "");
        map.put("depId", "");
        map.put("departure", go);
        map.put("departureDate", time);
        map.put("departureStation", "");
        //map.put("desId", 4098);
        map.put("destination",arr);
        map.put("dptTimeSpan", "");
        map.put("hasCategory", true);
        map.put("orderPrice", "");
        map.put("departureStation", 0);
        map.put("orderTime", 0);
        map.put("page", 1);
        map.put("pageSize", 3);
        map.put("departureStation", "");
        try {
            Connection conn = Jsoup.connect(url)
                    .timeout(15000)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .header("Accept", "application/json")
                    .header("Connection", "close")
                    //.header("Cookie", "JSESSIONID=104178E578E4A7F2FFE875F270C63D84; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=2564227338.64545.0000; RAIL_EXPIRATION=1642166013380; RAIL_DEVICEID=UZ6IRmoX3P91l4ljlx6rRoWny-rHOVPqq2LuyE3uGS9-KDWVKVyN2xOs6XnhV21mrPmS-0C2Ac6G0K62PFBentVEohK52kvh-oEfF7Lz4KmcUrwN0mdyRIqCJRxyj9T5J-Gbk8DeZaqnFs3JXOhuoeoR4BqWA9Ky; BIGipServerpassport=971505930.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; current_captcha_type=Z; _jc_save_toDate=2022-01-11; _jc_save_wfdc_flag=dc; BIGipServerportal=3067347210.16671.0000; _jc_save_fromDate=2022-01-12; _jc_save_toStation=%u4E0A%u6D77%u8679%u6865%2CAOH; _jc_save_fromStation=%u5317%u4EAC%u5357%2CVNP")
                    //.header("Cookie", "JSESSIONID=C634CCFE89D2A5FC70E84A9EC104FF53; tk=bNmGdZ7N_cW_FBPRhMEzvZ14LDKsSS1uJLP2q-mkt80gas1s0; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=2564227338.64545.0000; RAIL_EXPIRATION=1642166013380; RAIL_DEVICEID=UZ6IRmoX3P91l4ljlx6rRoWny-rHOVPqq2LuyE3uGS9-KDWVKVyN2xOs6XnhV21mrPmS-0C2Ac6G0K62PFBentVEohK52kvh-oEfF7Lz4KmcUrwN0mdyRIqCJRxyj9T5J-Gbk8DeZaqnFs3JXOhuoeoR4BqWA9Ky; BIGipServerpassport=971505930.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; current_captcha_type=Z; _jc_save_toDate=2022-01-11; _jc_save_wfdc_flag=dc; BIGipServerportal=3067347210.16671.0000; _jc_save_fromDate=2022-01-12; _jc_save_toStation=%u4E0A%u6D77%u8679%u6865%2CAOH; _jc_save_fromStation=%u5317%u4EAC%u5357%2CVNP; uKey=6fef9e4ccbf56e409bda5daca1047c80d1040e59faa6b989656288a855477057")
                    //.header("Cookie","JSESSIONID=C634CCFE89D2A5FC70E84A9EC104FF53; tk=bNmGdZ7N_cW_FBPRhMEzvZ14LDKsSS1uJLP2q-mkt80gas1s0; route=495c805987d0f5c8c84b14f60212447d; BIGipServerotn=2564227338.64545.0000; RAIL_EXPIRATION=1642166013380; RAIL_DEVICEID=UZ6IRmoX3P91l4ljlx6rRoWny-rHOVPqq2LuyE3uGS9-KDWVKVyN2xOs6XnhV21mrPmS-0C2Ac6G0K62PFBentVEohK52kvh-oEfF7Lz4KmcUrwN0mdyRIqCJRxyj9T5J-Gbk8DeZaqnFs3JXOhuoeoR4BqWA9Ky; BIGipServerpassport=971505930.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; uKey=6fef9e4ccbf56e409bda5daca1047c8011a08560cf5f84b5047955f858463867; current_captcha_type=Z; _jc_save_toStation=%u4E0A%u6D77%2CSHH; _jc_save_toDate=2022-01-11; _jc_save_wfdc_flag=dc; BIGipServerportal=3067347210.16671.0000; _jc_save_fromStation=%u5317%u4EAC%2CBJP; _jc_save_fromDate=2022-01-12")
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println((java.net.URLEncoder.encode("您好","utf-8")));
        ;
    }

}
