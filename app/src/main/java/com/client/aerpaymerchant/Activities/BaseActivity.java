package com.client.aerpaymerchant.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.User;
import com.client.aerpaymerchant.preference.PreferenceProvider;

public class BaseActivity extends AppCompatActivity {

    private Dialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * A function is used to show the Custom Progress Dialog.
     */
    void showProgressDialog() {
        mProgressDialog =new Dialog(this);

        mProgressDialog.setContentView(R.layout.dialog_custom_progress);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    void hideProgressDialog() {
        mProgressDialog.dismiss();
    }


    User getUser(){
       return new PreferenceProvider(this).getUser();
    }

    String getStoreId(){
       return new PreferenceProvider(this).getStoreID();
    }

    boolean isAlreadyLogin(){
        User user = getUser();
        if (user != null ){
            return true;
        }else
            return false;
    }

    interface onMapResult {
         void onMapResulting(String name);
    }
}