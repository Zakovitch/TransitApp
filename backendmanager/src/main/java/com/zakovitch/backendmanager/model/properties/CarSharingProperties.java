package com.zakovitch.backendmanager.model.properties;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class CarSharingProperties extends RouteProperties {

    private String address;

    private String model;

    private String licensePlate;

    private int fuelLevel;

    private String engineType;

    private String internalCleanliness;

    private String description;

    private int seats;

    private int doors;

    public CarSharingProperties(String address, String model, String licensePlate, int fuelLevel, String engineType, String internalCleanliness, String description, int seats, int doors) {
        this.address = address;
        this.model = model;
        this.licensePlate = licensePlate;
        this.fuelLevel = fuelLevel;
        this.engineType = engineType;
        this.internalCleanliness = internalCleanliness;
        this.description = description;
        this.seats = seats;
        this.doors = doors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getInternalCleanliness() {
        return internalCleanliness;
    }

    public void setInternalCleanliness(String internalCleanliness) {
        this.internalCleanliness = internalCleanliness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}
