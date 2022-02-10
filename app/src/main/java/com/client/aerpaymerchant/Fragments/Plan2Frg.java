package com.client.aerpaymerchant.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.client.aerpaymerchant.Activities.LoginActivity;
import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.preference.PreferenceProvider;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Plan2Frg extends BaseFrg{

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan2_frg1, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.btn_plan_rate)
    public void onClick() {
        new PreferenceProvider(getActivity()).saveProgramID("2");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
