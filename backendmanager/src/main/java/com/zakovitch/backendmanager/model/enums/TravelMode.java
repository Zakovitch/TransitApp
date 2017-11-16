package com.zakovitch.backendmanager.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zakovitch on 12/11/2017.
 * Travel Mode enum
 */

public enum TravelMode {

    @SerializedName("subway")
    SUBWAY,
    @SerializedName("walking")
    WALKING,
    @SerializedName("bus")
    BUS,
    @SerializedName("change")
    CHANGE,
    @SerializedName("driving")
    DRIVING,
    @SerializedName("parking")
    PARKING,
    @SerializedName("cycling")
    CYCLING,
    @SerializedName("setup")
    SETUP

}
