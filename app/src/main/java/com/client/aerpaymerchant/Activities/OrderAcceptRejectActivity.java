package com.client.aerpaymerchant.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.client.aerpaymerchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderAcceptRejectActivity extends BaseActivity {


    @BindView(R.id.ll_accept)
    LinearLayout llAccept;

    @BindView(R.id.ll_decline)
    LinearLayout llDecline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_accept_reject);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.ll_accept,R.id.ll_decline})
     void onButtonClick(View view){
        switch (view.getId()){
            case R.id.ll_accept:{
                finish();
                break;
            }
            case R.id.ll_decline:{
                finish();
                break;
            }

        }
    }
}