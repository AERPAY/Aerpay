package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryAddress {

@SerializedName("id")
@Expose
private Object id;
@SerializedName("user_id")
@Expose
private Object userId;
@SerializedName("lat")
@Expose
private Object lat;
@SerializedName("long")
@Expose
private Object _long;
@SerializedName("city")
@Expose
private Object city;
@SerializedName("state")
@Expose
private Object state;
@SerializedName("country")
@Expose
private Object country;
@SerializedName("zip")
@Expose
private Object zip;
@SerializedName("street")
@Expose
private Object street;
@SerializedName("apartment")
@Expose
private Object apartment;
@SerializedName("instructions")
@Expose
private Object instructions;
@SerializedName("default")
@Expose
private Object _default;
@SerializedName("created")
@Expose
private Object created;

public Object getId() {
return id;
}

public void setId(Object id) {
this.id = id;
}

public Object getUserId() {
return userId;
}

public void setUserId(Object userId) {
this.userId = userId;
}

public Object getLat() {
return lat;
}

public void setLat(Object lat) {
this.lat = lat;
}

public Object getLong() {
return _long;
}

public void setLong(Object _long) {
this._long = _long;
}

public Object getCity() {
return city;
}

public void setCity(Object city) {
this.city = city;
}

public Object getState() {
return state;
}

public void setState(Object state) {
this.state = state;
}

public Object getCountry() {
return country;
}

public void setCountry(Object country) {
this.country = country;
}

public Object getZip() {
return zip;
}

public void setZip(Object zip) {
this.zip = zip;
}

public Object getStreet() {
return street;
}

public void setStreet(Object street) {
this.street = street;
}

public Object getApartment() {
return apartment;
}

public void setApartment(Object apartment) {
this.apartment = apartment;
}

public Object getInstructions() {
return instructions;
}

public void setInstructions(Object instructions) {
this.instructions = instructions;
}

public Object getDefault() {
return _default;
}

public void setDefault(Object _default) {
this._default = _default;
}

public Object getCreated() {
return created;
}

public void setCreated(Object created) {
this.created = created;
}

}