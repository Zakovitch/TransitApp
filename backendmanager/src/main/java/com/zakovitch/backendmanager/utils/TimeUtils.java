package com.zakovitch.backendmanager.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zakovitch on 13/11/2017.
 */

public class TimeUtils {
    static final String TAG = "TimeUtils";
    /**
     * Get date from string
     * @param dateString
     * @return
     */
    public static Date getDateFromString(String dateString){
        Date convertedDate = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            convertedDate = simpleDateFormat.parse(dateString);
        }
        catch (ParseException ex) {
            //TODO handle exception
            Log.e(TAG,"Error parsing date");
        }
        return convertedDate;
    }


    public static int getTimeDifferentInMinutes(Date startDate, Date endDate){

        long different = endDate.getTime() - startDate.getTime();
        long seconds = different / 1000;
        long minutes = seconds / 60;

        return (int) minutes;
    }

    /**
     * Return time in string like 1h30m
     * @param minutes
     * @return
     */
    public static String getTimeInString(int minutes) {

        String dateDiffString ="";

        long elapsedHours = minutes / 60;
        int elapsedMinutes = minutes % 60;


        if(elapsedHours >= 1)
            dateDiffString +=elapsedHours+"h";
        if(elapsedMinutes > 0)
            dateDiffString +=elapsedMinutes+"m";

        return dateDiffString;

    }



}
