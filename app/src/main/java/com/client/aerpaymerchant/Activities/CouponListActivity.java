package com.client.aerpaymerchant.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponListActivity extends BaseActivity {

    @BindView(R.id.mLlAddCoupon)
    LinearLayout mLlAddCoupon;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    CouponsAdapter couponsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        ButterKnife.bind(this);

        couponsAdapter = new CouponsAdapter(CouponListActivity.this, new ArrayList<>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CouponListActivity.this));
        mRecyclerView.setAdapter(couponsAdapter);

        mLlAddCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CouponListActivity.this, AddCouponActivity.class));
            }
        });
    }



    //--------------------------------------Home Adapter-----------------------------------------
    public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.MyViewHolder> {

        Context context;
        List<String> childFeedList;

        public CouponsAdapter(Context context, List<String > childFeedList) {
            this.context = context;
            this.childFeedList = childFeedList;

        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item_design, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {




        }


        @Override
        public int getItemCount() {
            return 3;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


            public MyViewHolder(View itemView) {
                super(itemView);



            }
        }
    }
}