package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Msg {

    @SerializedName("User")
    @Expose
    private User user;
    @SerializedName("Country")
    @Expose
    private Country country;
    @SerializedName("store")
    @Expose
    private ArrayList<stores> stores;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ArrayList<stores> getStores() {
        return stores;
    }

    public void setStores(ArrayList<com.client.aerpaymerchant.model.stores> stores) {
        this.stores = stores;
    }


}
