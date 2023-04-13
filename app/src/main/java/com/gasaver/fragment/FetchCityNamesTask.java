package com.gasaver.fragment;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchCityNamesTask extends AsyncTask<LatLng, Void, List<String>> {

    private Context mContext;
    private AutoCompleteTextView mAutoCompleteTextView;

    public FetchCityNamesTask(Context context, AutoCompleteTextView autoCompleteTextView) {
        mContext = context;
        mAutoCompleteTextView = autoCompleteTextView;
    }

    @Override
    protected List<String> doInBackground(LatLng... params) {
        LatLng location = params[0];

        // Build the URL for the Geocoding API request
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + location.latitude + "," + location.longitude + "&result_type=locality" +"&key=AIzaSyA1B-lkYW4V5rR8PP3Zr9gUbBWZoR3hOkg";

        // Set up the HTTP client
        OkHttpClient client = new OkHttpClient();

        // Create the request object
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Send the request and extract the list of city names from the response
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsArray = jsonObject.getJSONArray("results");
            HashSet<String> cityNames = new HashSet<>();
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONArray addressComponents = resultsArray.getJSONObject(i).getJSONArray("address_components");
                for (int j = 0; j < addressComponents.length(); j++) {
                    JSONArray types = addressComponents.getJSONObject(j).getJSONArray("types");
                    cityNames.add(addressComponents.getJSONObject(j).getString("long_name"));
                    if (types.toString().contains("locality")) {
                        String cityName = addressComponents.getJSONObject(j).getString("long_name");
                        cityNames.add(cityName);
                    }
                }
            }
            return new ArrayList<>(cityNames);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<String> cityNames) {
        if (cityNames != null) {
            // Set up the autocomplete adapter with the list of city names
            ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_dropdown_item_1line, cityNames);
            mAutoCompleteTextView.setAdapter(adapter);
        } else {
            // Handle the case where the network operation failed
        }
    }
}
