package com.client.aerpaymerchant.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("delivery_address_id")
    @Expose
    private String deliveryAddressId;
    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("delivery")
    @Expose
    private String delivery;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("rider_tip")
    @Expose
    private String riderTip;
    @SerializedName("delivery_fee")
    @Expose
    private String deliveryFee;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("delivery_datetime")
    @Expose
    private String deliveryDatetime;
    @SerializedName("device")
    @Expose
    private String device;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("cart_random_id")
    @Expose
    private String cartRandomId;
    @SerializedName("created")
    @Expose
    private String created;

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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getRiderTip() {
        return riderTip;
    }

    public void setRiderTip(String riderTip) {
        this.riderTip = riderTip;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryDatetime() {
        return deliveryDatetime;
    }

    public void setDeliveryDatetime(String deliveryDatetime) {
        this.deliveryDatetime = deliveryDatetime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCartRandomId() {
        return cartRandomId;
    }

    public void setCartRandomId(String cartRandomId) {
        this.cartRandomId = cartRandomId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}