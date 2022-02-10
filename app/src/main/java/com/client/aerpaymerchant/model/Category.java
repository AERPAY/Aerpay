package com.client.aerpaymerchant.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

@SerializedName("id")
@Expose
private String id;
@SerializedName("store_id")
@Expose
private String storeId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("image")
@Expose
private String image;
@SerializedName("description")
@Expose
private String description;
@SerializedName("level")
@Expose
private String level;
@SerializedName("featured")
@Expose
private String featured;
@SerializedName("active")
@Expose
private String active;

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

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getLevel() {
return level;
}

public void setLevel(String level) {
this.level = level;
}

public String getFeatured() {
return featured;
}

public void setFeatured(String featured) {
this.featured = featured;
}

public String getActive() {
return active;
}

public void setActive(String active) {
this.active = active;
}

}


