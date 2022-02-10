package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("id")
@Expose
private String id;
@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("last_name")
@Expose
private String lastName;
@SerializedName("email")
@Expose
private String email;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("password")
@Expose
private String password;
@SerializedName("image")
@Expose
private String image;
@SerializedName("role")
@Expose
private String role;
@SerializedName("device_token")
@Expose
private String deviceToken;
@SerializedName("token")
@Expose
private String token;
@SerializedName("dob")
@Expose
private Object dob;
@SerializedName("active")
@Expose
private String active;
@SerializedName("country_id")
@Expose
private String countryId;
@SerializedName("admin_per_order_commission")
@Expose
private String adminPerOrderCommission;
@SerializedName("rider_fee_per_order")
@Expose
private String riderFeePerOrder;
@SerializedName("lat")
@Expose
private String lat;
@SerializedName("long")
@Expose
private String _long;
@SerializedName("online")
@Expose
private String online;
@SerializedName("created")
@Expose
private String created;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getRole() {
return role;
}

public void setRole(String role) {
this.role = role;
}

public String getDeviceToken() {
return deviceToken;
}

public void setDeviceToken(String deviceToken) {
this.deviceToken = deviceToken;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public Object getDob() {
return dob;
}

public void setDob(Object dob) {
this.dob = dob;
}

public String getActive() {
return active;
}

public void setActive(String active) {
this.active = active;
}

public String getCountryId() {
return countryId;
}

public void setCountryId(String countryId) {
this.countryId = countryId;
}

public String getAdminPerOrderCommission() {
return adminPerOrderCommission;
}

public void setAdminPerOrderCommission(String adminPerOrderCommission) {
this.adminPerOrderCommission = adminPerOrderCommission;
}

public String getRiderFeePerOrder() {
return riderFeePerOrder;
}

public void setRiderFeePerOrder(String riderFeePerOrder) {
this.riderFeePerOrder = riderFeePerOrder;
}

public String getLat() {
return lat;
}

public void setLat(String lat) {
this.lat = lat;
}

public String getLong() {
return _long;
}

public void setLong(String _long) {
this._long = _long;
}

public String getOnline() {
return online;
}

public void setOnline(String online) {
this.online = online;
}

public String getCreated() {
return created;
}

public void setCreated(String created) {
this.created = created;
}

}