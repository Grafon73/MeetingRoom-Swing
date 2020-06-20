package com.grafon.test.eventsDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CheckEventDate {
    String eventDate, hour;
    Date date;
    SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
    boolean datesFit;

    public CheckEventDate(String date, String hour) {
        eventDate = date;
        this.hour = hour;
    }

    public boolean doCheckTime(){
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Date Parse Error");
        }
        Date today = new Date();
        long difference = date.getTime() + Integer.parseInt(hour)*3600000 - today.getTime();
        if(difference > 1800000 && difference<86400000){
            datesFit = true;
        }else {
            datesFit = false;
        }

        return datesFit;
    }

}
