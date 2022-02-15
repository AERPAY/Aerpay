package com.client.aerpaymerchant.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.client.aerpaymerchant.R;

public class ManageDeliveryChargeActivity extends BaseActivity {

    Button mConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_delivery_charge);

        mConfirmBtn=findViewById(R.id.mConfirmBtn);
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageDeliveryChargeActivity.this,SettingsActivity.class));
            }
        });
    }
}