package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderModel {

    @SerializedName("active")
    @Expose
    private ArrayList<OrderDetailsModel> active = new ArrayList<>();

    @SerializedName("completed_orders")
    @Expose
    private ArrayList<OrderDetailsModel> completedOrders= new ArrayList<>();

    @SerializedName("pending_orders")
    @Expose
    private ArrayList<OrderDetailsModel> pendingOrders= new ArrayList<>();

    public ArrayList<OrderDetailsModel> getActive() {
        return active;
    }

    public void setActive(ArrayList<OrderDetailsModel> active) {
        this.active = active;
    }

    public ArrayList<OrderDetailsModel> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(ArrayList<OrderDetailsModel> completedOrders) {
        this.completedOrders = completedOrders;
    }

    public ArrayList<OrderDetailsModel> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(ArrayList<OrderDetailsModel> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
}