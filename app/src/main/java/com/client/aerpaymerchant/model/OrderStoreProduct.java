package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderStoreProduct {

@SerializedName("id")
@Expose
private String id;
@SerializedName("product_id")
@Expose
private String productId;
@SerializedName("order_id")
@Expose
private String orderId;
@SerializedName("product_title")
@Expose
private String productTitle;
@SerializedName("product_price")
@Expose
private String productPrice;
@SerializedName("product_quantity")
@Expose
private String productQuantity;
@SerializedName("product_image")
@Expose
private String productImage;
@SerializedName("product_size")
@Expose
private String productSize;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getProductId() {
return productId;
}

public void setProductId(String productId) {
this.productId = productId;
}

public String getOrderId() {
return orderId;
}

public void setOrderId(String orderId) {
this.orderId = orderId;
}

public String getProductTitle() {
return productTitle;
}

public void setProductTitle(String productTitle) {
this.productTitle = productTitle;
}

public String getProductPrice() {
return productPrice;
}

public void setProductPrice(String productPrice) {
this.productPrice = productPrice;
}

public String getProductQuantity() {
return productQuantity;
}

public void setProductQuantity(String productQuantity) {
this.productQuantity = productQuantity;
}

public String getProductImage() {
return productImage;
}

public void setProductImage(String productImage) {
this.productImage = productImage;
}

public String getProductSize() {
return productSize;
}

public void setProductSize(String productSize) {
this.productSize = productSize;
}

}