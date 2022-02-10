package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponUsed {

@SerializedName("id")
@Expose
private Object id;
@SerializedName("order_id")
@Expose
private Object orderId;
@SerializedName("coupon_id")
@Expose
private Object couponId;
@SerializedName("user_id")
@Expose
private Object userId;
@SerializedName("created")
@Expose
private Object created;

public Object getId() {
return id;
}

public void setId(Object id) {
this.id = id;
}

public Object getOrderId() {
return orderId;
}

public void setOrderId(Object orderId) {
this.orderId = orderId;
}

public Object getCouponId() {
return couponId;
}

public void setCouponId(Object couponId) {
this.couponId = couponId;
}

public Object getUserId() {
return userId;
}

public void setUserId(Object userId) {
this.userId = userId;
}

public Object getCreated() {
return created;
}

public void setCreated(Object created) {
this.created = created;
}

}
