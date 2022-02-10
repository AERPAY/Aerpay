package com.client.aerpaymerchant.Activities;

import android.app.Application;

import com.client.aerpaymerchant.preference.PreferenceProvider;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new PreferenceProvider(this.getApplicationContext());

    }
}
