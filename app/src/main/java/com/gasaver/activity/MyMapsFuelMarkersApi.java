package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.gasaver.R;
import com.gasaver.network.ApiInterface;

public class MyMapsFuelMarkersApi extends AppCompatActivity {

    //    public Api api;
    public ApiInterface apiInterface;
    public TextView textViewResult;
    public RecyclerView recy_sample;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_maps_fuel_markers_api);
    }
}