package com.client.aerpaymerchant.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.client.aerpaymerchant.Activities.HomeActivity;
import com.client.aerpaymerchant.Activities.SignupActivity;
import com.client.aerpaymerchant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class WelcomeFrg1 extends BaseFrg {

    Unbinder unbinder;

    @BindView(R.id.nextBtn)
    Button mNextBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_welcome_frg1, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.nextBtn)
    public void onClick() {
        if (isAlreadyLogin())
            startActivity(new Intent(getActivity(), HomeActivity.class));
        else startActivity(new Intent(getActivity(), SignupActivity.class));
        getActivity().finish();
    }
}