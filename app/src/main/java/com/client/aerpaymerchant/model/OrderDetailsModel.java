package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderDetailsModel   {

@SerializedName("Order")
@Expose
private Order order;

@SerializedName("User")
@Expose
private User user;

@SerializedName("DeliveryAddress")
@Expose
private DeliveryAddress deliveryAddress;

@SerializedName("Store")
@Expose
private Store store;

@SerializedName("CouponUsed")
@Expose
private CouponUsed couponUsed;

@SerializedName("OrderStoreProduct")
@Expose
private ArrayList<OrderStoreProduct> orderStoreProduct = new ArrayList<>();

public Order getOrder() {
return order;
}

public void setOrder(Order order) {
this.order = order;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public DeliveryAddress getDeliveryAddress() {
return deliveryAddress;
}

public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
this.deliveryAddress = deliveryAddress;
}

public Store getStore() {
return store;
}

public void setStore(Store store) {
this.store = store;
}

public CouponUsed getCouponUsed() {
return couponUsed;
}

public void setCouponUsed(CouponUsed couponUsed) {
this.couponUsed = couponUsed;
}

public ArrayList<OrderStoreProduct> getOrderStoreProduct() {
return orderStoreProduct;
}

public void setOrderStoreProduct(ArrayList<OrderStoreProduct> orderStoreProduct) {
this.orderStoreProduct = orderStoreProduct;
}

}