package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.client.aerpaymerchant.preference.PreferenceProvider;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity {

    @BindView(R.id.mConfirmBtn)
    Button mConfirmBtn;
    @BindView(R.id.mAlreadyHaveAct_tv)
    TextView mAlreadyHaveActTv;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.et_business)
    EditText mEtBusiness;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_owner)
    EditText mEtOwner;
    @BindView(R.id.et_refcode)
    EditText mEtRefcode;
    @BindView(R.id.mCategoryEt)
    EditText mCategoryEt;

    @BindView(R.id.mSubCategoryEt)
    EditText mSubCategoryEt;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.mCategoryLl)
    LinearLayout mCategoryLl;

    @BindView(R.id.til_address)
    TextInputLayout mTilAddress;

    @BindView(R.id.mSubCategoryDropdown)
    LinearLayout mSubCategoryDropdown;

    @BindView(R.id.mSubCategoryLl)
    LinearLayout mSubCategoryLl;

    BottomSheetDialog categoryDialog,subCategoryDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        setCategoriesDropdown();
        setSubCategoryDropdown();

        mTilAddress.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SignupActivity.this, MapsActivity.class),201);
            }
        });


    }

    @OnClick({R.id.mConfirmBtn, R.id.mAlreadyHaveAct_tv,R.id.mCategoryLl,R.id.mSubCategoryDropdown})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mConfirmBtn:
                signup();

                break;
            case R.id.mAlreadyHaveAct_tv:
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                break;

            case R.id.mCategoryLl:
                categoryDialog.show();
                break;
            case R.id.mSubCategoryDropdown:
                subCategoryDialog.show();
                break;
        }
    }

    private void setCategoriesDropdown() {
        View view = getLayoutInflater().inflate(R.layout.categories_layout, null);

        TextView mFoodTv=view.findViewById(R.id.mFoodTv);
        TextView mGroceryTv=view.findViewById(R.id.mGroceryTv);
        TextView mKiryanaTv=view.findViewById(R.id.mKiryanaTv);

        mFoodTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryEt.setText(mFoodTv.getText());
                mSubCategoryLl.setVisibility(View.VISIBLE);
                categoryDialog.dismiss();
            }
        });

        mGroceryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryEt.setText(mGroceryTv.getText());
                mSubCategoryLl.setVisibility(View.GONE);
                categoryDialog.dismiss();
            }
        });

        mKiryanaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryEt.setText(mKiryanaTv.getText());
                mSubCategoryLl.setVisibility(View.GONE);
                categoryDialog.dismiss();
            }
        });



        categoryDialog = new BottomSheetDialog(SignupActivity.this);
        categoryDialog.setContentView(view);
    }

    private void setSubCategoryDropdown() {
        View view = getLayoutInflater().inflate(R.layout.subcategories_layout, null);

        TextView mCafeTv=view.findViewById(R.id.mCafeTv);
        TextView mSweetTv=view.findViewById(R.id.mSweetTv);
        TextView mFastFoodTv=view.findViewById(R.id.mFastFoodTv);
        TextView mRestTv=view.findViewById(R.id.mRestTv);

        mCafeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubCategoryEt.setText(mCafeTv.getText());
                subCategoryDialog.dismiss();
            }
        });

        mSweetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubCategoryEt.setText(mSweetTv.getText());
                subCategoryDialog.dismiss();
            }
        });

        mFastFoodTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubCategoryEt.setText(mFastFoodTv.getText());
                subCategoryDialog.dismiss();
            }
        });

        mRestTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubCategoryEt.setText(mRestTv.getText());
                subCategoryDialog.dismiss();
            }
        });



        subCategoryDialog = new BottomSheetDialog(SignupActivity.this);
        subCategoryDialog.setContentView(view);
    }

    private void signup() {

        JsonObject object = new JsonObject();

        object.addProperty("phone",mEtMobile.getText().toString() );
        object.addProperty("address",mEtAddress.getText().toString() );
        object.addProperty("first_name",mEtBusiness.getText().toString() );
        object.addProperty("last_name",mEtBusiness.getText().toString() );
        object.addProperty("email",mEtEmail.getText().toString() );
        object.addProperty("owner",mEtOwner.getText().toString() );
        object.addProperty("refcode",mEtRefcode.getText().toString() );
        object.addProperty("category",mCategoryEt.getText().toString() );
        object.addProperty("sub_category",mSubCategoryEt.getText().toString() );
        object.addProperty("dob","01-01-2000");
        object.addProperty("country_id","91");
        object.addProperty("role","store");
        object.addProperty("password",etPassword.getText().toString());
        object.addProperty("device_token",new PreferenceProvider(this).getFCMToken());

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.REGISTER_MEMBER)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                        showToast(message);
                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        startActivity(new Intent(SignupActivity.this, SelectPlanActivity.class));
                        finish();
                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();

                    }

                }).makeCall();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 201 && resultCode == RESULT_OK){
                mEtAddress.setText(data.getStringExtra("ADDRESS"));
        }
    }
}