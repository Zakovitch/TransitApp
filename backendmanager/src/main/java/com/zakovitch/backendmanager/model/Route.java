package com.zakovitch.backendmanager.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.zakovitch.backendmanager.model.enums.RouteType;
import com.zakovitch.backendmanager.model.properties.BikeSharingProperties;
import com.zakovitch.backendmanager.model.properties.CarSharingProperties;
import com.zakovitch.backendmanager.model.properties.PrivateBikeProperties;
import com.zakovitch.backendmanager.model.properties.PublicTransportProperties;
import com.zakovitch.backendmanager.model.properties.RouteProperties;
import com.zakovitch.backendmanager.model.properties.TaxiProperties;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Route {

    /*
    "type": "car_sharing",
    "provider": "drivenow",
    "segments": [],
    "properties": {},
    "price": {}
     */

    @Nullable
    @SerializedName("type")
    private RouteType type;

    @Nullable
    @SerializedName("provider")
    private String provider;

    @Nullable
    @SerializedName("segments")
    private ArrayList<Segment> segments;

    @Nullable
    @SerializedName("properties")
    private Properties properties;

    @Nullable
    @SerializedName("price")
    private Price price;

    @Nullable
    public RouteType getType() {
        return type;
    }

    public void setType(@Nullable RouteType type) {
        this.type = type;
    }

    @Nullable
    public Provider getProvider(Map<String, Provider> providers) {
        return providers.get(provider);
    }

    @Nullable
    public String getProvider() {
        return provider;
    }

    public void setProvider(@Nullable String provider) {
        this.provider = provider;
    }

    @Nullable
    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(@Nullable ArrayList<Segment> segments) {
        this.segments = segments;
    }

    /*@Nullable
    public Properties getProperties() {
        return properties;
    }*/

    public void setProperties(@Nullable Properties properties) {
        this.properties = properties;
    }

    @Nullable
    public Price getPrice() {
        return price;
    }

    /*
        Returns price with currency ex: 15 Euro
     */
    public String getFullPrice() {
        return price.getAmount()+" "+price.getCyrrency();
    }

    public void setPrice(@Nullable Price price) {
        this.price = price;
    }


    public RouteProperties getProperties() {

        switch (type) {
            case BIKE_SHARING:
                return new BikeSharingProperties(properties.getId(), properties.getAvailableBikes());

            case CAR_SHARING:
                return new CarSharingProperties(properties.getAddress(), properties.getModel(), properties.getLicensePlate(), properties.getFuelLevel(), properties.getEngineType(), properties.getInternalCleanliness(), properties.getDescription(), properties.getSeats(), properties.getDoors());

            case TAXI:
                return new TaxiProperties(properties.getCompanies());

            case PRIVATE_BIKE:
                return new PrivateBikeProperties();

            case PUBLIC_TRANSPORT:
                return new PublicTransportProperties();

            default:
                return new RouteProperties();
        }
    }
}
