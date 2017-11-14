package com.zakovitch.bemyguide.utils;

import android.content.Context;

import com.zakovitch.backendmanager.model.enums.RouteType;
import com.zakovitch.backendmanager.model.enums.TravelMode;
import com.zakovitch.bemyguide.R;

/**
 * Created by Zakovitch on 14/11/2017.
 */

public class StringUtils {

    /**
     * Return text for routeType
     * @param routeType
     * @param context
     * @return
     */
    public static String getRouteTypeString(RouteType routeType, Context context){

         int resId=0;
        switch (routeType){
            case PUBLIC_TRANSPORT:
                resId= R.string.public_transport;
                break;
            case BIKE_SHARING:
                resId= R.string.bike_sharing;
                break;
            case PRIVATE_BIKE:
                resId= R.string.private_bike;
                break;
            case TAXI:
                resId= R.string.taxi;
                break;
            case CAR_SHARING:
                resId= R.string.car_sharing;
                break;
        }

        return context.getString(resId);
    }

    /**
     * Return text of TravelMode
     * @param travelMode
     * @param context
     * @return
     */
    public static String getTravelModeString(TravelMode travelMode, Context context){

        int resId=0;
        switch (travelMode){
            case WALKING:
                resId= R.string.walk_mode;
                break;
            case CHANGE:
                resId= R.string.change;
                break;
            case SETUP:
                resId= R.string.setup;
                break;
            case DRIVING:
                resId= R.string.driving;
                break;
            case PARKING:
                resId= R.string.parking;
                break;
            case SUBWAY:
                resId= R.string.subway;
                break;
            case BUS:
                resId= R.string.bus;
                break;
            case CYCLING:
                resId= R.string.cycling;
                break;
        }

        return context.getString(resId);
    }
}
