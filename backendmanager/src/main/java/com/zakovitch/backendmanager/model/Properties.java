package com.zakovitch.backendmanager.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zakovitch.backendmanager.model.enums.RouteType;
import com.zakovitch.backendmanager.model.properties.BikeSharingProperties;
import com.zakovitch.backendmanager.model.properties.CarSharingProperties;
import com.zakovitch.backendmanager.model.properties.PrivateBikeProperties;
import com.zakovitch.backendmanager.model.properties.PublicTransportProperties;
import com.zakovitch.backendmanager.model.properties.RouteProperties;
import com.zakovitch.backendmanager.model.properties.TaxiProperties;

import java.util.ArrayList;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Properties {

    @Nullable
    @SerializedName("id")
    @Expose
    private int id;

    @Nullable
    @SerializedName("available_bikes")
    @Expose
    private int availableBikes;

    @Nullable
    @SerializedName("companies")
    @Expose
    private ArrayList<Companies> companies;

    /*
        "address": "Linienstraße 72, 10119 Berlin",
        "model": "BMW X1",
        "license_plate": "M-DX9224",
        "fuel_level": 72,
        "engine_type": "Diesel",
        "internal_cleanliness": "Good",
        "description": "Jorge",
        "seats": null,
        "doors": null
   */

    @Nullable
    @SerializedName("address")
    @Expose
    private String address;

    @Nullable
    @SerializedName("model")
    @Expose
    private String model;

    @Nullable
    @SerializedName("license_plate")
    @Expose
    private String licensePlate;

    @Nullable
    @SerializedName("fuel_level")
    @Expose
    private int fuelLevel;

    @Nullable
    @SerializedName("engine_type")
    @Expose
    private String engineType;

    @Nullable
    @SerializedName("internal_cleanliness")
    @Expose
    private String internalCleanliness;

    @Nullable
    @SerializedName("description")
    @Expose
    private String description;

    @Nullable
    @SerializedName("seats")
    @Expose
    private int seats;

    @Nullable
    @SerializedName("doors")
    @Expose
    private int doors;

    @Nullable
    public int getId() {
        return id;
    }

    public void setId(@Nullable int id) {
        this.id = id;
    }

    @Nullable
    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(@Nullable int availableBikes) {
        this.availableBikes = availableBikes;
    }

    @Nullable
    public ArrayList<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(@Nullable ArrayList<Companies> companies) {
        this.companies = companies;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    @Nullable
    public String getModel() {
        return model;
    }

    public void setModel(@Nullable String model) {
        this.model = model;
    }

    @Nullable
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(@Nullable String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Nullable
    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(@Nullable int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @Nullable
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(@Nullable String engineType) {
        this.engineType = engineType;
    }

    @Nullable
    public String getInternalCleanliness() {
        return internalCleanliness;
    }

    public void setInternalCleanliness(@Nullable String internalCleanliness) {
        this.internalCleanliness = internalCleanliness;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public int getSeats() {
        return seats;
    }

    public void setSeats(@Nullable int seats) {
        this.seats = seats;
    }

    @Nullable
    public int getDoors() {
        return doors;
    }

    public void setDoors(@Nullable int doors) {
        this.doors = doors;
    }


    public RouteProperties getProperties(RouteType routeType){

        switch (routeType) {
            case BIKE_SHARING:
                return new BikeSharingProperties(getId(),getAvailableBikes());

            case CAR_SHARING:
                return new CarSharingProperties(getAddress(),getModel(),getLicensePlate(),getFuelLevel(),getEngineType(),getInternalCleanliness(),getDescription(),getSeats(),getDoors());

            case TAXI:
                return new TaxiProperties(getCompanies());

            case PRIVATE_BIKE:
                return new PrivateBikeProperties();

            case PUBLIC_TRANSPORT:
                return new PublicTransportProperties();

            default:
                return new RouteProperties();
        }
    }
}
