package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.client.aerpaymerchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCouponActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mSelectBtn)
    Button mSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.mSelectBtn)
    public void onClick() {
        startActivity(new Intent(AddCouponActivity.this,StoreProductsActivity.class));
    }
}