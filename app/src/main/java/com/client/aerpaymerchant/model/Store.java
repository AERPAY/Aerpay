package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

@SerializedName("id")
@Expose
private String id;
@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("about")
@Expose
private String about;
@SerializedName("logo")
@Expose
private String logo;
@SerializedName("cover")
@Expose
private String cover;
@SerializedName("shipping_base_fee")
@Expose
private String shippingBaseFee;
@SerializedName("shipping_fee_per_distance")
@Expose
private String shippingFeePerDistance;
@SerializedName("distance_unit")
@Expose
private String distanceUnit;
@SerializedName("active")
@Expose
private String active;
@SerializedName("created")
@Expose
private String created;
@SerializedName("StoreLocation")
@Expose
private StoreLocation storeLocation;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getAbout() {
return about;
}

public void setAbout(String about) {
this.about = about;
}

public String getLogo() {
return logo;
}

public void setLogo(String logo) {
this.logo = logo;
}

public String getCover() {
return cover;
}

public void setCover(String cover) {
this.cover = cover;
}

public String getShippingBaseFee() {
return shippingBaseFee;
}

public void setShippingBaseFee(String shippingBaseFee) {
this.shippingBaseFee = shippingBaseFee;
}

public String getShippingFeePerDistance() {
return shippingFeePerDistance;
}

public void setShippingFeePerDistance(String shippingFeePerDistance) {
this.shippingFeePerDistance = shippingFeePerDistance;
}

public String getDistanceUnit() {
return distanceUnit;
}

public void setDistanceUnit(String distanceUnit) {
this.distanceUnit = distanceUnit;
}

public String getActive() {
return active;
}

public void setActive(String active) {
this.active = active;
}

public String getCreated() {
return created;
}

public void setCreated(String created) {
this.created = created;
}

public StoreLocation getStoreLocation() {
return storeLocation;
}

public void setStoreLocation(StoreLocation storeLocation) {
this.storeLocation = storeLocation;
}

}