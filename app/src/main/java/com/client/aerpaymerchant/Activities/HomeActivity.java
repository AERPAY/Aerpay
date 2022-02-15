package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.adapters.OrderAdapter;
import com.client.aerpaymerchant.model.Order;
import com.client.aerpaymerchant.model.OrderDetailsModel;
import com.client.aerpaymerchant.model.OrderModel;
import com.client.aerpaymerchant.model.OrdersResModel;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.client.aerpaymerchant.preference.NotificationUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.mSettingsImg)
    ImageView mSettingsImg;

    @BindView(R.id.mStatsImg)
    ImageView mStatsImg;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mManageStoreLl)
    LinearLayout mManageStoreLl;

    @BindView(R.id.mCreateOffersLl)
    LinearLayout mCreateOffersLl;

    @BindView(R.id.mManageOrdersLl)
    LinearLayout mManageOrdersLl;

    @BindView(R.id.rv_orders)
    RecyclerView rvOrders;

    @BindView(R.id.mPendingLl)
    RadioButton rbPending;

    OrderAdapter orderAdapter;
    OrderModel model = new OrderModel();

    static OrderDetailsModel orderDetailsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mSettingsImg.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this,SettingsActivity.class));

        });

        setupData();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrders();


    }

    private void setupData() {
        orderAdapter = new OrderAdapter(new ArrayList<>(), model -> {
            Intent intent;
            if (rbPending.isChecked()) {
                intent = new Intent(HomeActivity.this, OrderAcceptRejectActivity.class);
            } else
                intent = new Intent(HomeActivity.this, OrderDetailsActivity.class);

            orderDetailsModel = (model);
            startActivity(intent);
            return null;
        });
        rvOrders.setAdapter(orderAdapter);
    }

    @OnClick({R.id.mSettingsImg, R.id.mStatsImg, R.id.mManageStoreLl, R.id.mCreateOffersLl,
            R.id.mManageOrdersLl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mSettingsImg:
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            case R.id.mStatsImg:
                startActivity(new Intent(HomeActivity.this, StatsActivity.class));
                break;
            case R.id.mManageStoreLl:
                startActivity(new Intent(HomeActivity.this, StoreProductsActivity.class));
                break;
            case R.id.mManageOrdersLl:
                startActivity(new Intent(HomeActivity.this, OrderHistory.class));
                break;
//            case R.id.orderHistoryLl:
//                startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class));
//                break;
            case R.id.mCreateOffersLl:
                startActivity(new Intent(HomeActivity.this, CouponListActivity.class));
                break;
        }
    }

    @OnCheckedChanged({R.id.mPendingLl, R.id.rb_completed, R.id.rb_accept, R.id.rb_indeli})
    public void onCheckchange(CompoundButton view, boolean aBoolean) {
        if (aBoolean) {
            switch (view.getId()) {
                case R.id.rb_accept:
                    orderAdapter.setList(model.getActive());
                    break;
                case R.id.rb_completed:
                    orderAdapter.setList(model.getCompletedOrders());
                    break;

                default:
                    orderAdapter.setList(model.getPendingOrders());

                    break;
            }
        }
    }


    private void getOrders() {
        JsonObject object = new JsonObject();

        object.addProperty("user_id", getUser().getId());

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.GET_ORDERS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();

                        try {
                            OrdersResModel resModel = new Gson().fromJson(response, OrdersResModel.class);
                            if (resModel.getMsg() != null) {
                                model = resModel.getMsg();
                                orderAdapter.setList(model.getPendingOrders());
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }
}