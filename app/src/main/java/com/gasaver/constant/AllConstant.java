package com.gasaver.constant;

import com.gasaver.PlaceModel;
import com.gasaver.R;

import java.util.ArrayList;
import java.util.Arrays;

public interface AllConstant {

    int STORAGE_REQUEST_CODE = 1000;
    int LOCATION_REQUEST_CODE = 2000;
    String IMAGE_PATH = "/Profile/image_profile.jpg";


    ArrayList<PlaceModel> placesName = new ArrayList<>(
            Arrays.asList(
//                    new PlaceModel(1, R.drawable.ic_restaurant, "Restaurant", "restaurant"),
                    new PlaceModel(1, R.drawable.ic_restaurant, "Gas", "India"),

//                    new PlaceModel(2, R.drawable.ic_atm, "ATM", "atm"),
                    new PlaceModel(2, R.drawable.ic_atm, "Fuel", "fuel_station"),

                    new PlaceModel(3, R.drawable.ic_gas_station, "Petrol", "Petrol"),

//                    new PlaceModel(4, R.drawable.ic_shopping_cart, "Groceries", "supermarket"),
                    new PlaceModel(4, R.drawable.ic_shopping_cart, "Biodiesel", "Biodiesel"),

//                    new PlaceModel(5, R.drawable.ic_hotel, "Hotels", "hotel"),
                    new PlaceModel(5, R.drawable.ic_hotel, "E10", "E10"),

//                    new PlaceModel(6, R.drawable.ic_pharmacy, "Pharmacies", "pharmacy"),
                    new PlaceModel(6, R.drawable.ic_pharmacy, "Prem DSL", "Prem DSL"),

//                    new PlaceModel(7, R.drawable.ic_hospital, "Hospitals & Clinics", "hospital"),
                    new PlaceModel(7, R.drawable.ic_hospital, "U95 & U98", "U95 & U98"),

//                    new PlaceModel(8, R.drawable.ic_car_wash, "Car Wash", "car_wash"),
                    new PlaceModel(8, R.drawable.ic_car_wash, "AdBlue", "AdBlue"),


//                    new PlaceModel(9, R.drawable.ic_saloon, "Beauty Salons", "beauty_salon")
                    new PlaceModel(9, R.drawable.ic_saloon, "Unlead 91", "Unlead 91")


            )
    );

}
