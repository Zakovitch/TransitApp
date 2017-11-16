package com.zakovitch.backendmanager.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.zakovitch.backendmanager.model.enums.TravelMode;
import com.zakovitch.backendmanager.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Segment {
    /*
        "name": null,
        "num_stops": 0,
        "stops": [],
        "travel_mode": "walking",
        "description": null,
        "color": "#b1becc",
        "icon_url": "https://d3m2tfu2xpiope.cloudfront.net/vehicles/walking.svg",
        "polyline": "uvr_I{yxpA?d@?X@V@z@FdD@`@@T@P??FIHILI~@e@`Ak@??AaC"
     */

    @Nullable
    @SerializedName("name")
    private String name;

    @Nullable
    @SerializedName("num_stops")
    private int numStops;

    @Nullable
    @SerializedName("stops")
    private ArrayList<Stop> stops;

    @Nullable
    @SerializedName("travel_mode")
    private TravelMode travelMode;

    @Nullable
    @SerializedName("description")
    private String description;

    @Nullable
    @SerializedName("color")
    private String color;

    @Nullable
    @SerializedName("icon_url")
    private String iconUrl;

    @Nullable
    @SerializedName("polyline")
    private String polyline;

    //segment time in minutes
    private int segmentTime;


    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public int getNumStops() {
        return numStops;
    }

    public void setNumStops(@Nullable int numStops) {
        this.numStops = numStops;
    }

    @Nullable
    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void setStops(@Nullable ArrayList<Stop> stops) {
        this.stops = stops;
    }

    @Nullable
    public TravelMode getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(@Nullable TravelMode travelMode) {
        this.travelMode = travelMode;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getColor() {
        return color;
    }

    public void setColor(@Nullable String color) {
        this.color = color;
    }

    @Nullable
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(@Nullable String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Nullable
    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(@Nullable String polyline) {
        this.polyline = polyline;
    }

    /**
     * Calculate segment time by make a sum of all stops time duration
     *
     * @return segment time in minutes
     */
    public int getSegmentTime() {
        if(numStops >=0){
            if (numStops == 0) numStops++;
            Date startDate = TimeUtils.getDateFromString(stops.get(0).getDatetime());
            Date endDate = TimeUtils.getDateFromString(stops.get(numStops).getDatetime());
            return TimeUtils.getTimeDifferentInMinutes(startDate,endDate);

        }else return 0;

    }

    public void setSegmentTime(int segmentTime) {
    }
}
