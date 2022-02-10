package com.client.aerpaymerchant.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    GoogleMap mGoogleMap;

    ActivityMapsBinding binding;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.btnAddProduct.setOnClickListener(view -> onBackPressed());

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap=googleMap;

        mGoogleMap.setOnMapClickListener(latLng -> {
            mGoogleMap.clear();
            mGoogleMap.addMarker(new MarkerOptions().position(latLng));
            getAddress(this,latLng.latitude,latLng.longitude);
        });
    }

    public void getAddress(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            address = obj.getAddressLine(0);
            binding.etPlace.setText(obj.getAddressLine(0));
            binding.etBuilding.setText(obj.getSubLocality());
            binding.etLane.setText(obj.getSubAdminArea());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            binding.etPlace.setText("NA");
            binding.etBuilding.setText("NA");
            binding.etLane.setText("NA");
        }
    }

    @Override
    public void onBackPressed() {
       if (address.length() > 0){
           Intent intent = new Intent();
           intent.putExtra("ADDRESS",address);
           setResult(RESULT_OK,intent);
       } else
           setResult(RESULT_CANCELED);

        finish();
    }
}