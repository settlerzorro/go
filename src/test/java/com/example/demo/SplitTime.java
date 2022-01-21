package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SplitTime {

    public static void main(String[] args) throws ParseException {
        String startStr="2012-5-27";
        String endStr="2012-6-27";

        SimpleDateFormat sdf= new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date startDate =sdf.parse(startStr);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Long startTIme = start.getTimeInMillis();

        Date endDate =sdf.parse(endStr);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        Long endTime = end.getTimeInMillis();



        Long oneDay = 1000 * 60 * 60 * 24L;

        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(df.format(d));
            time += oneDay;
        }

    }
}
