package com.client.aerpaymerchant.Fragments;

import androidx.fragment.app.Fragment;

import com.client.aerpaymerchant.model.User;
import com.client.aerpaymerchant.preference.PreferenceProvider;

public class BaseFrg extends Fragment {


    boolean isAlreadyLogin(){
        User user = new PreferenceProvider(getActivity()).getUser();
        if (user != null ){
            return true;
        }else
            return false;
    }
    String geStoreId(){
        return new PreferenceProvider(getActivity()).getStoreID();
    }
}