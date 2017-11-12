package com.zakovitch.backendmanager.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class Price {

    @SerializedName("currency")
    private String cyrrency;

    @SerializedName("amount")
    private int amount;

    public String getCyrrency() {
        return cyrrency;
    }

    public void setCyrrency(String cyrrency) {
        this.cyrrency = cyrrency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
