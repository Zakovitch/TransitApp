package com.zakovitch.backendmanager.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Stop {

    /*
     "lat": 52.5302356,
     "lng": 13.4033659,
     "datetime": "2015-04-17T12:29:00+01:00",
     "name": "Torstraße 103, 10119 Berlin, Deutschland",
     "properties": null
    */

    @Nullable
    @SerializedName("lat")
    private float lat;

    @Nullable
    @SerializedName("lng")
    private float lng;

    @Nullable
    @SerializedName("datetime")
    private String datetime;

    @Nullable
    @SerializedName("name")
    private String name;

    @Nullable
    @SerializedName("properties")
    private String properties;


    @Nullable
    public float getLat() {
        return lat;
    }

    public void setLat(@Nullable float lat) {
        this.lat = lat;
    }

    @Nullable
    public float getLng() {
        return lng;
    }

    public void setLng(@Nullable float lng) {
        this.lng = lng;
    }

    @Nullable
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(@Nullable String datetime) {
        this.datetime = datetime;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getProperties() {
        return properties;
    }

    public void setProperties(@Nullable String properties) {
        this.properties = properties;
    }
}
