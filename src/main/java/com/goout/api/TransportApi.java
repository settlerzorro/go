package com.goout.api;

import com.goout.airplane.entity.Air;
import com.goout.airplane.service.IAirService;
import com.goout.bus.entity.Bus;
import com.goout.bus.service.IBusService;
import com.goout.ship.entity.Ship;
import com.goout.ship.service.IShipService;
import com.goout.train.model.request.GetTicketListRequest;
import com.goout.train.model.response.RestResponse;
import com.goout.train.model.response.Train;
import com.goout.train.service.ITrainTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/transportApi/",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
public class TransportApi {
    @Autowired
    private IBusService busService;

    @Autowired
    private IShipService shipService;

    @Autowired
    private IAirService airService;

    @Autowired
    private ITrainTicketService trainTicketService;

    /**
     * 火车最快
     */
    private static final String TRAIN_FAST_TIME = "TRAIN_FAST_TIME";
    /**
     * 火车最便宜
     */
    private static final String TRAIN_CHEAP_TIME = "TRAIN_CHEAP_TIME";



    @GetMapping(value = "getTransportList")
    public RestResponse getTransportListHandler(@RequestParam(value = "userId",required = false) Integer userId, HttpServletRequest request
            , @RequestParam("fromCity") String fromCity
            , @RequestParam("toCity") String toCity
            , @RequestParam("fromDate") String fromDate) throws ParseException {
        Map map = new HashMap();
        SimpleDateFormat sdfd =new SimpleDateFormat("yyy-MM-dd");
        Bus bus = new Bus();
        bus.setDptStation(fromCity);
        bus.setArrStation(toCity);
        bus.setDptDate(fromDate);
        List<Bus> busList = busService.getBusList(userId,bus);

        busList.sort(Comparator.comparingDouble((Bus a) -> a.getTicketPrice().doubleValue()));
        if(busList.size() != 0){
            if(busList.size() >= 3){
                map.put("bus_cheap",busList.subList(0,3));
            }else{
                map.put("bus_cheap",busList);
            }
        }
        List<Ship> shipList = new ArrayList();
        if(toCity.contains("烟台") || toCity.contains("威海")){
            Ship ship = new Ship();
            ship.setFromStation(fromCity);
            ship.setToStation(toCity);
            shipList = shipService.getShipList(userId,ship);
            Comparator<Ship> byName = Comparator.comparing(Ship::getTdPrice,Comparator.nullsLast(BigDecimal::compareTo));
            Comparator<Ship> byName2 = Comparator.comparing(Ship::getYdPrice,Comparator.nullsLast(BigDecimal::compareTo));
            Comparator<Ship> byName3 = Comparator.comparing(Ship::getEdAprice,Comparator.nullsLast(BigDecimal::compareTo));
            Comparator<Ship> byName4 = Comparator.comparing(Ship::getEdBprice,Comparator.nullsLast(BigDecimal::compareTo));
            Comparator<Ship> byName5 = Comparator.comparing(Ship::getSdAPrice,Comparator.nullsLast(BigDecimal::compareTo));
            Comparator<Ship> byName6 = Comparator.comparing(Ship::getSdBPrice,Comparator.nullsLast(BigDecimal::compareTo));
            Comparator<Ship> byName7 = Comparator.comparing(Ship::getSdPrice,Comparator.nullsLast(BigDecimal::compareTo));
            Comparator<Ship> byName8 = Comparator.comparing(Ship::getSxPrice,Comparator.nullsLast(BigDecimal::compareTo));
            shipList.sort(byName.thenComparing(byName2).thenComparing(byName3).thenComparing(byName4).thenComparing(byName5).thenComparing(byName6).thenComparing(byName7).thenComparing(byName8));
            if(shipList.size() != 0){
                if(shipList.size() >=3){
                    map.put("ship",shipList.subList(0,3));
                }else
                    map.put("ship",shipList);
            }
        }

        Air air = new Air();
        air.setOapName(fromCity);
        air.setAapName(toCity);
        air.setFromTime(fromDate);
        List airList = airService.getAirList(userId,air);
        Comparator<Air> byName = Comparator.comparing(Air::getUseTime);
        Comparator<Air> byEtdDepartTimeDesc =Comparator.comparing(Air::getAep);
        airList.sort(byName.thenComparing(byEtdDepartTimeDesc));
        if(airList.size() != 0){
            if(airList.size() >=3){
                map.put("air",airList.subList(0,3));
            }else
                map.put("air",airList);
        }


        GetTicketListRequest train = new GetTicketListRequest();
        train.setFromStation(fromCity);
        train.setToStation(toCity);
        Date dat =sdfd.parse(fromDate);
        train.setFromDate(dat);
        List<Train> trainList = trainTicketService.getTicketList(userId,train);
        List<Train> timeTrainList = deepCopy(trainList);
        List<Train> priceTrainList = deepCopy(trainList);//二等座-硬卧-一等座-软卧-硬座

        Collections.sort(timeTrainList, new Comparator<Train>() {
            @Override
            public int compare(Train o1, Train o2) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                try {
                    Date dt1 = format.parse(o1.getRunTime());
                    Date dt2 = format.parse(o2.getRunTime());
                    if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        //二等座-硬卧-一等座-软卧-硬座
        Collections.sort(priceTrainList, new Comparator<Train>() {
            @Override
            public int compare(Train o1, Train o2) {
                try {
                    if(o1.getEdzPrice() != null && o2.getEdzPrice() != null){
                        if(o1.getEdzPrice().doubleValue() < o2.getEdzPrice().doubleValue()){
                            return -1;
                        } else {
                            return 1;
                        }
                    } else if(o1.getEdzPrice() != null){
                        return -1;
                    } else if(o2.getEdzPrice() != null){
                        return 1;
                    }

                    else if(o1.getYwPrice() != null && o2.getYwPrice() != null){
                        if(o1.getYwPrice().doubleValue() < o2.getYwPrice().doubleValue()){
                            return -1;
                        } else{
                            return 1;
                        }
                    } else if(o1.getYwPrice() != null){
                        return -1;
                    } else if(o2.getYwPrice() != null){
                        return 1;
                    }

                    else if(o1.getYdzPrice() != null && o2.getYdzPrice() != null){
                        if(o1.getYdzPrice().doubleValue() < o2.getYdzPrice().doubleValue()){
                            return -1;
                        } else{
                            return 1;
                        }
                    } else if(o1.getYdzPrice() != null){
                        return -1;
                    } else if(o2.getYdzPrice() != null){
                        return 1;
                    }

                    else if(o1.getRwPrice() != null && o2.getRwPrice() != null){
                        if(o1.getRwPrice().doubleValue() < o2.getRwPrice().doubleValue()){
                            return -1;
                        } else{
                            return 1;
                        }
                    } else if(o1.getRwPrice() != null){
                        return -1;
                    } else if(o2.getRwPrice() != null){
                        return 1;
                    }


                    else if(o1.getYzPrice() != null && o2.getYzPrice() != null){
                        if(o1.getYzPrice().doubleValue() < o2.getYzPrice().doubleValue()){
                            return -1;
                        } else{
                            return 1;
                        }
                    } else if(o1.getYzPrice() != null){
                        return -1;
                    } else if(o2.getYzPrice() != null){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }

        });
        List<Train> newPriceTrainList = new ArrayList<>();
        if(priceTrainList.size() >=3){
            priceTrainList = priceTrainList.subList(0,3);//只保留3个
        }

        if(timeTrainList.size() >=3){
            timeTrainList = timeTrainList.subList(0,3);//只保留3个
        }
        priceTrainList.addAll(timeTrainList);
        Iterator<Train> it = priceTrainList.iterator();
        //Iterator<Train> newIt = newPriceTrainList.iterator();
        while(it.hasNext()){
            Train tr = it.next();
            if(newPriceTrainList.size() == 0){
                newPriceTrainList.add(tr);
            }else{
                boolean flag = false;
                for(int i = 0;i<newPriceTrainList.size();i++){
                    if(Objects.equals(tr.getTrainCode(), newPriceTrainList.get(i).getTrainCode())){
                        flag = true;
                    }
                }
                if(!flag){
                    newPriceTrainList.add(tr);
                }
            }
        }
        if(newPriceTrainList.size() != 0){
            map.put("train",newPriceTrainList);
        }
        if(map.size() == 0){
            map.put("drive","没有找到合适列车、航班、轮渡、大巴，建议自驾前往目的地");
        }
        return RestResponse.succuess(map);
    }

    public static <T> List<T> deepCopy(List<T> src) {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteOut);
        ) {
            outputStream.writeObject(src);
            try (ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                 ObjectInputStream inputStream = new ObjectInputStream(byteIn);
            ) {
                return (List<T>) inputStream.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
