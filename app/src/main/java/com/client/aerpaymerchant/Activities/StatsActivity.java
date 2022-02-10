package com.client.aerpaymerchant.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsActivity extends BaseActivity {

    BottomSheetDialog dialog, categoryDialog;

    @BindView(R.id.mDaysLl)
    LinearLayout mDaysLl;

    @BindView(R.id.mCategoriesLl)
    LinearLayout mCategoriesLl;

    @BindView(R.id.mDateLl)
    LinearLayout mDateLl;

    @BindView(R.id.mDayEt)
    EditText mDayEt;

    @BindView(R.id.mCategoryEt)
    EditText mCategoryEt;

    private int mYear, mMonth, mDay;
    private Calendar selectedCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ButterKnife.bind(this);

        setDaysMenu();
        setCategoriesDropdown();

        mDaysLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        mCategoriesLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialog.show();
            }
        });

        mDateLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateDialog();
            }
        });
    }

    private void startDateDialog() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(StatsActivity.this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
            String fmonth, fDate;
            int month;

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                try {
                    if (monthOfYear < 10 && dayOfMonth < 10) {

                        fmonth = "0" + monthOfYear;
                        month = Integer.parseInt(fmonth) + 1;
                        fDate = "0" + dayOfMonth;
                        String paddedMonth = String.format("%02d", month);
                        //mDobEt.setText(fDate + "/" + paddedMonth + "/" + year);

                    } else {

                        fmonth = "0" + monthOfYear;
                        month = Integer.parseInt(fmonth) + 1;
                        String paddedMonth = String.format("%02d", month);
                        //mDobEt.setText(dayOfMonth + "/" + paddedMonth + "/" + year);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                selectedCal = Calendar.getInstance();
                selectedCal.set(Calendar.YEAR, year);
                selectedCal.set(Calendar.MONTH, monthOfYear);
                selectedCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    private void setCategoriesDropdown() {
        View view = getLayoutInflater().inflate(R.layout.store_categories_layout, null);

        categoryDialog = new BottomSheetDialog(StatsActivity.this);
        categoryDialog.setContentView(view);
    }

    private void setDaysMenu() {
        View view = getLayoutInflater().inflate(R.layout.menu_days_layout, null);

        dialog = new BottomSheetDialog(StatsActivity.this);
        dialog.setContentView(view);

    }


    private void getOrder() {
        JsonObject object = new JsonObject();

        object.addProperty("d", "");
        object.addProperty("sd", "");

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
                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }
}