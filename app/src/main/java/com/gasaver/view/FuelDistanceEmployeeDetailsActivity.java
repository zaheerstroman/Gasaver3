package com.gasaver.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gasaver.AppSharedpref;
import com.gasaver.R;
import com.gasaver.Response.StationData_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FuelDistanceEmployeeDetailsActivity extends AppCompatActivity {

    AppSharedpref appSharedpref;
    Context context = FuelDistanceEmployeeDetailsActivity.this;
    //    List<Root> list = new ArrayList<>();
//    List<StationDataJsonSchema2PojoResponseGasaver.Data> list = new ArrayList<>();
    List<StationData_0.Data> list = new ArrayList<>();

    TextView userid, username, useremail, userphonenumber, userAddressi,

    useraddress, useraddresstreet, useraddresssuite, useraddresscity, useraddresszipcode,
            userLatitude, userLongitude, userBrand, userDistance,
            usercompanydetails, usercompanyname, userwebsite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_distance_employee_details);

        appSharedpref = new AppSharedpref(context);
        list = appSharedpref.getemployeelistdetailsdata();

//        List<Root> postElectronicsRequests = list;
//        List<StationDataJsonSchema2PojoResponseGasaver.Data> postElectronicsRequests = list;
        List<StationData_0.Data> postElectronicsRequests = list;


        userid = findViewById(R.id.userid);
        username = findViewById(R.id.username);
        useremail = findViewById(R.id.useremail);
        userAddressi = findViewById(R.id.userAddressi);

//        useraddress = findViewById(R.id.useraddress);
        useraddresstreet = findViewById(R.id.useraddresstreet);
        useraddresssuite = findViewById(R.id.useraddresssuite);
        useraddresscity = findViewById(R.id.useraddresscity);
        useraddresszipcode = findViewById(R.id.useraddresszipcode);
        userphonenumber = findViewById(R.id.userphonenumber);
        usercompanydetails = findViewById(R.id.usercompanydetails);
        usercompanyname = findViewById(R.id.usercompanyname);
        userwebsite = findViewById(R.id.userwebsite);

        userLatitude = findViewById(R.id.userLatitude);
        userLongitude = findViewById(R.id.userLongitude);
        userBrand = findViewById(R.id.userBrand);
        userDistance = findViewById(R.id.userDistance);


//        List<Root> listview = new ArrayList<>();
//        List<StationDataJsonSchema2PojoResponseGasaver.Data> listview = new ArrayList<>();
        List<StationData_0.Data> listview = new ArrayList<>();


        for (int j = 0; j < postElectronicsRequests.size(); j++) {

            userid.setText(String.valueOf(postElectronicsRequests.get(j).getId()));
//            username.setText( String.valueOf(postElectronicsRequests.get(j).getUsername()));
            username.setText(String.valueOf(postElectronicsRequests.get(j).getStationid()));
//            useremail.setText( String.valueOf(postElectronicsRequests.get(j).getEmail().toLowerCase(Locale.ROOT)));
            useremail.setText(String.valueOf(postElectronicsRequests.get(j).getAddress().toLowerCase(Locale.ROOT)));

//            userphonenumber.setText( String.valueOf(postElectronicsRequests.get(j).getPhone()));
            userphonenumber.setText(String.valueOf(postElectronicsRequests.get(j).getDepartmentId()));

            userAddressi.setText(String.valueOf(postElectronicsRequests.get(j).getAddress()));

//            useraddresstreet.setText(String.valueOf(postElectronicsRequests.get(j).getPrices().toLowerCase(Locale.ROOT)));
            useraddresstreet.setText(String.valueOf(postElectronicsRequests.get(j).getPrices()));
//            useraddresstreet.setText( String.valueOf(postElectronicsRequests.get(j).getAddress().getStreet()));
//            useraddresssuite.setText( String.valueOf(postElectronicsRequests.get(j).getAddress().getSuite()));

//            useraddresssuite.setText(String.valueOf(postElectronicsRequests.get(j).getPrices()));
//            useraddresssuite.setText(String.valueOf(postElectronicsRequests.get(j).Data.getData().getPrices().getId()));

//            useraddresscity.setText( String.valueOf(postElectronicsRequests.get(j).getAddress().getCity()));
//            useraddresscity.setText(String.valueOf(postElectronicsRequests.get(j).getAddress().getType()));

//            useraddresszipcode.setText( String.valueOf(postElectronicsRequests.get(j).getAddress().getZipcode()));
//            useraddresszipcode.setText(String.valueOf(postElectronicsRequests.get(j).getAddress().getAmount()));


//            latitude
            userLatitude.setText(String.valueOf(postElectronicsRequests.get(j).getLatitude()));
            userLongitude.setText(String.valueOf(postElectronicsRequests.get(j).getLongitude()));
            userBrand.setText(String.valueOf(postElectronicsRequests.get(j).getBrand()));
            userDistance.setText(String.valueOf(postElectronicsRequests.get(j).getDistance()));


//            listview.add(postElectronicsRequests.get(j));
            listview.add(postElectronicsRequests.get(j));


//            usercompanydetails.setText( String.valueOf(postElectronicsRequests.get(j).getCompany()));

//            prices:---

//            usercompanyname.setText( String.valueOf(postElectronicsRequests.get(j).getCompany().getName()));
//            usercompanyname.setText( String.valueOf(postElectronicsRequests.get(j).getPrices().getId()));

//            userwebsite.setText( String.valueOf(postElectronicsRequests.get(j).getWebsite()));
//            userwebsite.setText( String.valueOf(postElectronicsRequests.get(j).getType()));
//            userwebsite.setText( String.valueOf(postElectronicsRequests.get(j).getAmout()));


            userphonenumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < listview.size(); j++) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);

//                        callIntent.setData(Uri.parse("tel:"+String.valueOf(listview.get(j).getPhone())));
//                        callIntent.setData(Uri.parse("tel:" + String.valueOf(listview.get(j).getCity())));
                        callIntent.setData(Uri.parse("tel:" + String.valueOf(listview.get(j).getDepartmentId())));

//                    callIntent.setData(Uri.parse("tel:"+ getPackageManager().getPackagesHoldingPermissions()));
                        startActivity(callIntent);
//                    overridePendingTransition(R.anim.trans_left_in,R.anim.trans_left_out);
                        if (ActivityCompat.checkSelfPermission(FuelDistanceEmployeeDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }

                    }


                }
            });
            useremail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < listview.size(); j++) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
//                        intent.putExtra(Intent.EXTRA_EMAIL ,listview.get(j).getEmail());
                        intent.putExtra(Intent.EXTRA_EMAIL, listview.get(j).getId());
//                        intent.putExtra(Intent.EXTRA_EMAIL ,listview.get(j).getMobile());
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

                        startActivity(Intent.createChooser(intent, "Email via..."));
                    }

                }
            });
        }


    }
}