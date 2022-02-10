package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.client.aerpaymerchant.Fragments.Plan1Frg;
import com.client.aerpaymerchant.Fragments.Plan2Frg;
import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.adapters.ViewPagerAdapter2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPlanActivity extends BaseActivity {

    ViewPagerAdapter2 myAdapter;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plan);
        ButterKnife.bind(this);

        myAdapter = new ViewPagerAdapter2(getSupportFragmentManager());
        addPagerFragments();
        mViewPager.setAdapter(myAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void addPagerFragments() {
        myAdapter.addFragment(new Plan1Frg());
        myAdapter.addFragment(new Plan2Frg());
    }

    private void launchLoginScreen() {
        //SharedPreference.setFirstTimeLaunch(false);
        startActivity(new Intent(SelectPlanActivity.this, SignupActivity.class));
        //finish();
    }

}