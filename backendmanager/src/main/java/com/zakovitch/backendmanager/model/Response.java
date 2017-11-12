package com.zakovitch.backendmanager.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Response {

    @Nullable
    @SerializedName("routes")
    private ArrayList<Route> routes;

    @Nullable
    @SerializedName("provider_attributes")
    private Map<String, Provider> providers;


    @Nullable
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(@Nullable ArrayList<Route> routes) {
        this.routes = routes;
    }

    @Nullable
    public Map<String, Provider> getProviders() {
        return providers;
    }

    public void setProviders(@Nullable Map<String, Provider> providers) {
        this.providers = providers;
    }
}
