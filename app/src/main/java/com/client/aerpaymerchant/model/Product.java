package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

@SerializedName("id")
@Expose
private String id;
@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("store_id")
@Expose
private String storeId;
@SerializedName("title")
@Expose
private String title;
@SerializedName("description")
@Expose
private String description;
@SerializedName("size")
@Expose
private String size;
@SerializedName("price")
@Expose
private String price;
@SerializedName("sale_price")
@Expose
private String salePrice;
@SerializedName("updated")
@Expose
private String updated;
@SerializedName("created")
@Expose
private String created;
@SerializedName("favourite")
@Expose
private String favourite;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getCategoryId() {
return categoryId;
}

public void setCategoryId(String categoryId) {
this.categoryId = categoryId;
}

public String getStoreId() {
return storeId;
}

public void setStoreId(String storeId) {
this.storeId = storeId;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getSize() {
return size;
}

public void setSize(String size) {
this.size = size;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public String getSalePrice() {
return salePrice;
}

public void setSalePrice(String salePrice) {
this.salePrice = salePrice;
}

public String getUpdated() {
return updated;
}

public void setUpdated(String updated) {
this.updated = updated;
}

public String getCreated() {
return created;
}

public void setCreated(String created) {
this.created = created;
}

public String getFavourite() {
return favourite;
}

public void setFavourite(String favourite) {
this.favourite = favourite;
}

}