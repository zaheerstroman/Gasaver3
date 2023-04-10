package com.gasaver.userdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.gasaver.Response.StationData_0;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPreference {


    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "FUEL";
    private static final String categoryItem = "categoryItem";
    private static final String prices = "prices";


    @SuppressLint("CommitPrefEdits")
    public UserPreference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //public void setCategoryItem

    //    public void setcategoryItem(List<Item> productsInCartList) {
    public void setcategoryItem(List<StationData_0.Data.Prices> productsInCartList) {
//    public void setcategoryItem(List<StationData_0.Data> productsInCartList) {

        SharedPreferences.Editor editor;
        editor = pref.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        String adjn = gson.toJson(productsInCartList);

        editor.putString(categoryItem, adjn);
        editor.apply();
        editor.commit();
    }

    //    public List<Item> getcategoryItem() {
//    public List<StationData_0.Data> getcategoryItem() {
    public List<StationData_0.Data.Prices> getcategoryItem() {


//        List<Item> servicesList = null;
//        List<StationData_0.Data> servicesList = null;
        List<StationData_0.Data.Prices> servicesList = null;


        try {
            if (pref.contains(categoryItem)) {
                String jsonFavorites = pref.getString(categoryItem, null);
                Gson gson = new Gson();
//                Item[] favoriteItems = gson.fromJson(jsonFavorites,
//                        Item[].class);

//                StationData_0.Data[] favoriteItems = gson.fromJson(jsonFavorites,
//                        StationData_0.Data[].class);


                StationData_0.Data.Prices[] favoriteItems = gson.fromJson(jsonFavorites,
                        StationData_0.Data.Prices[].class);

                servicesList = Arrays.asList(favoriteItems);
//               servicesList = new ArrayList<>(servicesList);
            } else {
                servicesList = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return servicesList;
    }

    //    public void setvegcategoryItem(List<Restaurent> productsInCartList) {
    public void setvegcategoryItem(List<StationData_0.Data> productsInCartList) {

        SharedPreferences.Editor editor;
        editor = pref.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        String adjn = gson.toJson(productsInCartList);
//        Gson gson = new Gson();
//        String cartList = gson.toJson(productsInCartList);
        editor.putString(categoryItem, adjn);
        editor.apply();
        editor.commit();
    }

    //    public List<Restaurent> getvegcategoryItem() {
    public List<StationData_0.Data> getvegcategoryItem() {

//        List<Restaurent> servicesList = null;
        List<StationData_0.Data> servicesList = null;

        try {
            if (pref.contains(categoryItem)) {
                String jsonFavorites = pref.getString(categoryItem, null);
                Gson gson = new Gson();
//                Restaurent[] favoriteItems = gson.fromJson(jsonFavorites,
//                        Restaurent[].class);

                StationData_0.Data[] favoriteItems = gson.fromJson(jsonFavorites,
                        StationData_0.Data[].class);

                servicesList = Arrays.asList(favoriteItems);
//               servicesList = new ArrayList<>(servicesList);
            } else {
                servicesList = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return servicesList;
    }


//    public void setcategoryItem(List<Item> items) {
//
//    }

//    public void setcardlistItem(List<Item> productsInCartList) {

    public void setcardlistItem(List<StationData_0.Data.Prices> productsInCartList) {

        SharedPreferences.Editor editor;
        editor = pref.edit();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        Gson gson = gsonBuilder.create();
        String adjn = gson.toJson(productsInCartList);

        editor.putString(categoryItem, adjn);
        editor.apply();
        editor.commit();
    }

    //    public List<Item> getcardlistItem() {
    public List<StationData_0.Data.Prices> getcardlistItem() {

//        List<Item> servicesList = null;
        List<StationData_0.Data.Prices> servicesList = null;

        try {
            if (pref.contains(categoryItem)) {
                String jsonFavorites = pref.getString(categoryItem, null);
                Gson gson = new Gson();
//                Item[] favoriteItems = gson.fromJson(jsonFavorites,
//                        Item[].class);
                StationData_0.Data.Prices[] favoriteItems = gson.fromJson(jsonFavorites,
                        StationData_0.Data.Prices[].class);

                servicesList = Arrays.asList(favoriteItems);
//               servicesList = new ArrayList<>(servicesList);
            } else {
                servicesList = new ArrayList<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return servicesList;
    }


}
