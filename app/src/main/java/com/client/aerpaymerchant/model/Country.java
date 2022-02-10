package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

@SerializedName("id")
@Expose
private String id;
@SerializedName("iso")
@Expose
private String iso;
@SerializedName("name")
@Expose
private String name;
@SerializedName("country")
@Expose
private String country;
@SerializedName("iso3")
@Expose
private String iso3;
@SerializedName("numcode")
@Expose
private String numcode;
@SerializedName("country_code")
@Expose
private String countryCode;
@SerializedName("currency_code")
@Expose
private String currencyCode;
@SerializedName("currency_symbol")
@Expose
private String currencySymbol;
@SerializedName("active")
@Expose
private String active;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getIso() {
return iso;
}

public void setIso(String iso) {
this.iso = iso;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getIso3() {
return iso3;
}

public void setIso3(String iso3) {
this.iso3 = iso3;
}

public String getNumcode() {
return numcode;
}

public void setNumcode(String numcode) {
this.numcode = numcode;
}

public String getCountryCode() {
return countryCode;
}

public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

public String getCurrencyCode() {
return currencyCode;
}

public void setCurrencyCode(String currencyCode) {
this.currencyCode = currencyCode;
}

public String getCurrencySymbol() {
return currencySymbol;
}

public void setCurrencySymbol(String currencySymbol) {
this.currencySymbol = currencySymbol;
}

public String getActive() {
return active;
}

public void setActive(String active) {
this.active = active;
}

}