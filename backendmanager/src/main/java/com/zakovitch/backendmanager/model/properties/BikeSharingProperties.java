package com.zakovitch.backendmanager.model.properties;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class BikeSharingProperties extends RouteProperties {

    private int id;

    private int availableBikes;

    public BikeSharingProperties(int id, int availableBikes) {
        this.id = id;
        this.availableBikes = availableBikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }
}
