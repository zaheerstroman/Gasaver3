package com.gasaver.adapter;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.gasaver.Response.StationDataResponse;
import com.gasaver.databinding.InfoWindowLayoutBinding;
import com.gasaver.utils.CommonUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


    private InfoWindowLayoutBinding binding;
    private Context context;


    public InfoWindowAdapter( Context context) {

        this.context = context;

        binding = InfoWindowLayoutBinding.inflate(LayoutInflater.from(context), null, false);

    }

    @Override
    public View getInfoWindow(Marker marker) {

//        StationDataResponse
//        StationDataModel
//        by
//        PriceModel, DefaultDataModel

       StationDataResponse.StationDataModel stationDataModel=new Gson().fromJson( marker.getSnippet(), StationDataResponse.StationDataModel.class);
//        StationDataResponse.StationDataModel.DefaultDataModel stationDataModel=new Gson().fromJson( marker.getSnippet(), StationDataResponse.StationDataModel.DefaultDataModel.class);

        String markerTag = (String) marker.getTag();
        Log.d("TAG", "onMarkerClick: " + markerTag);

        Location location=new Location("");
        location.setLatitude(Double.parseDouble(stationDataModel.getLatitude()));
        location.setLongitude(Double.parseDouble(stationDataModel.getLongitude()));

        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marker.hideInfoWindow();
            }
        });



        binding.txtLocationName.setText(stationDataModel.getBrand());


        double distance = SphericalUtil.computeDistanceBetween(new LatLng(location.getLatitude(), location.getLongitude()), marker.getPosition());

        if (distance > 1000) {
            double kilometers = distance / 1000;
            binding.txtLocationDistance.setText(CommonUtils.roundUpDecimal(distance, 2) + " KM");
        }

        else {
            binding.txtLocationDistance.setText(CommonUtils.roundUpDecimal(distance, 2) + " Meters");

        }

        float speed = location.getSpeed();


        if (speed > 0) {
            double time = distance / speed;
            binding.txtLocationTime.setText(CommonUtils.roundUpDecimal(time, 2) + " sec");
        }


        else {
            binding.txtLocationTime.setText("N/A");
        }

        binding.txtBrand.setText(stationDataModel.getBrand());
        try {


                binding.txtPriceLocationDistance.setText("");
            binding.txtPriceLocationDistance2.setText("");
            binding.txtPriceLocationDistance3.setText("");

//            for (int i = 0; i < total; i++){
//                        for (int i = 0; i < txtPriceLocationDistance; i++){
//            }

            String latesttDTE=null;

//            double smalVal= Double.parseDouble(stationDataModel.getPrices().get(0).getAmount());
//            DefaultDataModel
            double smalVal= Double.parseDouble(stationDataModel.getDefault_data().get(0).getAmount());
            double smalVal2= Double.parseDouble(stationDataModel.getDefault_data().get(0).getAmount());
            double smalVal3= Double.parseDouble(stationDataModel.getDefault_data().get(0).getAmount());

//            for (StationDataResponse.PriceModel priceModel : stationDataModel.getPrices()) {
            for (StationDataResponse.DefaultDataModel defaultDataModel : stationDataModel.getDefault_data()) {

//                Double am= Double.valueOf(priceModel.getAmount());
                Double am= Double.valueOf(defaultDataModel.getAmount());
                Double am2= Double.valueOf(defaultDataModel.getAmount());
                Double am3= Double.valueOf(defaultDataModel.getAmount());

                if (latesttDTE == null)
//                if (latesttDTE == markerTag)

//                    latesttDTE = priceModel.getLastupdated();
                    latesttDTE = defaultDataModel.getAmount();

//                else if (CommonUtils.getDate(latesttDTE).getTime() >= (CommonUtils.getDate(defaultDataModel.getAmount()).getTime())) {
//                    latesttDTE = defaultDataModel.getAmount();
//                }

                if (smalVal>am)
                    smalVal=am;

                if (smalVal2>am2)
                    smalVal2=am2;

                if (smalVal3>am3)
                    smalVal3=am3;

//                else if (CommonUtils.getDate(latesttDTE).getTime() >= (CommonUtils.getDate(defaultDataModel.getAmount()).getTime())) {
//                    latesttDTE = defaultDataModel.getAmount();
//                }
            }

            binding.txtPriceLocationDistance.append(latesttDTE);
            binding.txtPriceLocationDistance2.append(latesttDTE);
            binding.txtPriceLocationDistance3.append(latesttDTE);


            binding.txtPriceLocationDistance.setText("$ " +smalVal);
            binding.txtPriceLocationDistance2.setText("$ " +smalVal2);
            binding.txtPriceLocationDistance3.setText("$ " +smalVal3);



//            binding.stationLayout.txtLastUpdated.append(latesttDTE);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return binding.getRoot();
    }


    @Override
    public View getInfoContents(Marker marker) {

        String markerTag = (String) marker.getTag();
        Log.d("TAG", "onMarkerClick: " + markerTag);

        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cvStation.setVisibility(View.GONE);
            }
        });

        return binding.getRoot();
    }
}
