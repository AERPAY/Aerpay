package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.client.aerpaymerchant.R;

public class SplashActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            if (isAlreadyLogin())
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            else startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
            finish();
        }, SPLASH_TIME_OUT);
    }
}