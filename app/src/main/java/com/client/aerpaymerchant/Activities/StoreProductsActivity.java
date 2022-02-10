package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.adapters.ProductAdapter;
import com.client.aerpaymerchant.model.ProductDetails;
import com.client.aerpaymerchant.model.ProductResModel;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreProductsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mAddNewProductBtn)
    Button mAddNewProductBtn;

    @BindView(R.id.rv_product)
    RecyclerView rvProduct;

    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_products);
        ButterKnife.bind(this);

        setupData();
    }

    private void setupData() {
        productAdapter = new ProductAdapter(this,new ArrayList<>(),(pos, id) -> {
            deleteProduct(pos, id);
            return null;
        });
        rvProduct.setAdapter(productAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    @OnClick(R.id.mAddNewProductBtn)
    public void onClick() {
        startActivity(new Intent(StoreProductsActivity.this, AddProductActivity.class));
    }


    private void getProducts(){
        JsonObject object = new JsonObject();

        object.addProperty("store_id", getStoreId());
        object.addProperty("user_id",getUser().getId() );

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.GET_PRODUCTS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try{
                            ProductResModel resModel = new Gson().fromJson(response,ProductResModel.class);
                            if(resModel.getCode() == 200){
                                    if (resModel.getMsg().size() > 0){
                                        productAdapter.setList((ArrayList<ProductDetails>) resModel.getMsg());
                                    }
                            }else
                                showToast(jsonObject.getString("msg"));

                        }catch (Exception e){
                            e.printStackTrace();
                            try {
                                showToast(jsonObject.getString("msg"));
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }


    private void deleteProduct(Integer pos,String id){
        JsonObject object = new JsonObject();

        object.addProperty("product_id", id);

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.GET_PRODUCTS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try{
                            ProductResModel resModel = new Gson().fromJson(response,ProductResModel.class);
                            if(resModel.getCode() == 200){
                                if (resModel.getMsg().size() > 0){
                                    productAdapter.removePos(pos);
                                }
                            }else
                                showToast(jsonObject.getString("msg"));

                        }catch (Exception e){
                            e.printStackTrace();
                            try {
                                showToast(jsonObject.getString("msg"));
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }
}