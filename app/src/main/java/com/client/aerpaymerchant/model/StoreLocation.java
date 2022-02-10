package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreLocation {

@SerializedName("id")
@Expose
private String id;
@SerializedName("store_id")
@Expose
private String storeId;
@SerializedName("lat")
@Expose
private String lat;
@SerializedName("long")
@Expose
private String _long;
@SerializedName("city")
@Expose
private String city;
@SerializedName("state")
@Expose
private String state;
@SerializedName("street")
@Expose
private String street;
@SerializedName("zip_code")
@Expose
private String zipCode;
@SerializedName("country_id")
@Expose
private String countryId;
@SerializedName("created")
@Expose
private String created;
@SerializedName("Country")
@Expose
private Country country;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getStoreId() {
return storeId;
}

public void setStoreId(String storeId) {
this.storeId = storeId;
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

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getStreet() {
return street;
}

public void setStreet(String street) {
this.street = street;
}

public String getZipCode() {
return zipCode;
}

public void setZipCode(String zipCode) {
this.zipCode = zipCode;
}

public String getCountryId() {
return countryId;
}

public void setCountryId(String countryId) {
this.countryId = countryId;
}

public String getCreated() {
return created;
}

public void setCreated(String created) {
this.created = created;
}

public Country getCountry() {
return country;
}

public void setCountry(Country country) {
this.country = country;
}

}