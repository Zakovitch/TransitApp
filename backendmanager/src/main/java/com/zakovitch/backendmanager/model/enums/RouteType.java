package com.zakovitch.backendmanager.model.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zakovitch on 12/11/2017.
 * Route Type enum
 */

public enum RouteType {
    @SerializedName("public_transport")
    PUBLIC_TRANSPORT,
    @SerializedName("car_sharing")
    CAR_SHARING,
    @SerializedName("private_bike")
    PRIVATE_BIKE,
    @SerializedName("bike_sharing")
    BIKE_SHARING,
    @SerializedName("taxi")
    TAXI

}
