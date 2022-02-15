package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.client.aerpaymerchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mUpdateProfileLl)
    LinearLayout mUpdateProfileLl;

    @BindView(R.id.mHelpDeskLl)
    LinearLayout mHelpDeskLl;

    @BindView(R.id.mDiscountLl)
    LinearLayout mDiscountLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mUpdateProfileLl, R.id.mHelpDeskLl, R.id.mDiscountLl,R.id.mManageDeliveryLl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mUpdateProfileLl:
                startActivity(new Intent(SettingsActivity.this, UpdateProfile.class));
                break;
            case R.id.mHelpDeskLl:
                startActivity(new Intent(SettingsActivity.this, QueryActivit.class));
                break;
            case R.id.mManageDeliveryLl:
                startActivity(new Intent(SettingsActivity.this, ManageDeliveryChargeActivity.class));
                break;
            case R.id.mDiscountLl:
                break;
        }
    }
}