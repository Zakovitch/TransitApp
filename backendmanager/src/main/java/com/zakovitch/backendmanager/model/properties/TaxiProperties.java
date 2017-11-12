package com.zakovitch.backendmanager.model.properties;

import com.zakovitch.backendmanager.model.Companies;

import java.util.ArrayList;

/**
 * Created by Zakovitch on 12/11/2017.
 */

public class TaxiProperties extends RouteProperties {

    private ArrayList<Companies> companies;

    public TaxiProperties(ArrayList<Companies> companies) {
        this.companies = companies;
    }

    public ArrayList<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Companies> companies) {
        this.companies = companies;
    }
}
