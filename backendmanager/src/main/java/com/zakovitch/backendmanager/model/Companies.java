package com.zakovitch.backendmanager.model;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Companies {

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
