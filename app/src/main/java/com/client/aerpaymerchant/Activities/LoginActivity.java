package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.LoginResModel;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.client.aerpaymerchant.preference.PreferenceProvider;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.mloginBtn)
    Button mloginBtn;

    @BindView(R.id.mCreateAccountTv)
    TextView mCreateAccountTv;

    @BindView(R.id.et_email)
    EditText mEmail;

    @BindView(R.id.et_password)
    EditText mPassword;
    private String TAG="LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(task -> {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }

                            // Get new FCM registration token
                            String token = task.getResult();


                            Log.d(TAG, token);
                            login(token);
                        });



            }
        });

        mCreateAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));

            }
        });
    }

    private void login(String token){

        JsonObject object = new JsonObject();

        object.addProperty("email",mEmail.getText().toString());
        object.addProperty("password",mPassword.getText().toString());
        object.addProperty("role","store");
        object.addProperty("device_token",token);

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.SIGN_IN)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                        showToast(message);
                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try{
                            LoginResModel resModel = new Gson().fromJson(response,LoginResModel.class);
                            if(resModel.getCode() == 200){
                                new PreferenceProvider(LoginActivity.this).saveUser(resModel.getMsg().getUser());
                                new PreferenceProvider(LoginActivity.this).saveStoreID(resModel.getMsg().getStores().get(0).getStoreLocation().getStoreId());
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                finish();
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