package com.client.aerpaymerchant.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductResModel {

@SerializedName("code")
@Expose
private Integer code;
@SerializedName("msg")
@Expose
private List<ProductDetails> msg = new ArrayList<>();

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

public List<ProductDetails> getMsg() {
return msg;
}

public void setMsg(List<ProductDetails> msg) {
this.msg = msg;
}

}