package com.client.aerpaymerchant.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

import com.client.aerpaymerchant.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInsightActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mMenuLl)
    LinearLayout mMenuLl;

    @BindView(R.id.mDateLl)
    LinearLayout mDateLl;

    BottomSheetDialog dialog;
    private int mYear, mMonth, mDay;
    private Calendar selectedCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_insight);
        ButterKnife.bind(this);

        setDaysMenu();

        mMenuLl.setOnClickListener(v -> dialog.show());

        mDateLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateDialog();
            }
        });
    }


    private void setDaysMenu() {
        View view = getLayoutInflater().inflate(R.layout.menu_days_layout, null);

        dialog = new BottomSheetDialog(ProductInsightActivity.this);
        dialog.setContentView(view);

    }

    private void startDateDialog() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(ProductInsightActivity.this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
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
}