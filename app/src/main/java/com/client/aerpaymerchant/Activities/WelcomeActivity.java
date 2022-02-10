package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.adapters.SliderAdapter;
import com.client.aerpaymerchant.adapters.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    //Variables
    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted,next_btn;

    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);
        next_btn = findViewById(R.id.next_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        //Dots
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAlreadyLogin())
                    startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                else startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
                finish();
            }
        });


    }

    public void skip(View view) {
        if (isAlreadyLogin())
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        else startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {

        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorAccent));

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setText(Html.fromHtml("&#8212;"));
        }

    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

          /*  if (position == 0) {
                letsGetStarted.setVisibility(View.VISIBLE);
                next_btn.setVisibility(View.INVISIBLE);
                letsGetStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
                        finish();
                    }
                });
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.VISIBLE);
                next_btn.setVisibility(View.INVISIBLE);
                letsGetStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
                        finish();
                    }
                });
            } else {
                next_btn.setVisibility(View.GONE);
                letsGetStarted.setVisibility(View.VISIBLE);
                letsGetStarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
                        finish();
                    }
                });            }*/

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}