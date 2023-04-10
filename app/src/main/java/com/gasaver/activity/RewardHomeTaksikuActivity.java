package com.gasaver.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gasaver.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

//public class RewardHomeTaksikuActivity extends AppCompatActivity {
public class RewardHomeTaksikuActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btn_request_reward;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_taksiku);


//        setTitleHome(R.id.toolbar_taksiku_reward, R.id.iv_title_reward, R.drawable.ic_burger, R.drawable.gas_saver_logo_rectangular_empty);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_reward);
        mapFragment.getMapAsync(this);

        btn_request_reward = (Button) findViewById(R.id.bt_request_reward);
        btn_request_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Home.this, SelectDriver.class));
//                startActivity(new Intent(HomeTaksikuActivity.this, SelectDriverTaksikuActivity.class));
//                startActivity(new Intent(RewardHomeTaksikuActivity.this, OrderTaksikuActivity.class));
                startActivity(new Intent(RewardHomeTaksikuActivity.this, RewardMyTripTaksikuActivity.class));

            }
        });

    }

    public Bitmap getBitmapFromView(String title, int dotBg) {

        LinearLayout llmarker_reward = (LinearLayout) findViewById(R.id.ll_marker_reward);
        TextView markerImageView = (TextView) findViewById(R.id.tv_title_reward);
        markerImageView.setText(title);
        View dot = (View) findViewById(R.id.dot_marker_reward);
        dot.setBackgroundResource(dotBg);

        llmarker_reward.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(llmarker_reward.getMeasuredWidth(), llmarker_reward.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        llmarker_reward.layout(0, 0, llmarker_reward.getMeasuredWidth(), llmarker_reward.getMeasuredHeight());
        llmarker_reward.draw(canvas);
        return bitmap;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e("Style", "Style parsing failed.");
        }
//        LatLng jakarta = new LatLng(-6.232812, 106.820933);
        LatLng jakarta = new LatLng(17.435413608990665, 78.44543199385824);
//        LatLng southjakarta = new LatLng(-6.22865,106.8151753);
        LatLng southjakarta = new LatLng(17.437682366574744,78.44324867581615);
        LatLng southjakarta1 = new LatLng(17.441927113417748,78.44164760451417);
        LatLng southjakarta2 = new LatLng(17.440486290497002,78.44275074875797);
        LatLng southjakarta3 = new LatLng(17.438268653813147,78.4428032794365);
        LatLng southjakarta4 = new LatLng(17.4375472071235,78.443923565414);
        LatLng southjakarta5 = new LatLng(17.430510999724927,78.44834158039095);
        LatLng southjakarta6 = new LatLng(17.428962183338523,78.45017475407718);
        LatLng southjakarta7 = new LatLng(17.426549375231296,78.45286929849675);









//        mMap.addMarker(new MarkerOptions().position(jakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Current Location", R.drawable.dot_pickup))));
//        mMap.addMarker(new MarkerOptions().position(southjakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Go Location", R.drawable.dot_dropoff))));


        mMap.addMarker(new MarkerOptions().position(jakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("700\nmm", R.drawable.petrolbank))));
        mMap.addMarker(new MarkerOptions().position(southjakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("0.8 km", R.drawable.petrolbank_01))));
        mMap.addMarker(new MarkerOptions().position(southjakarta1).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("1.3\nkm", R.drawable.petrolbank_02))));
        mMap.addMarker(new MarkerOptions().position(southjakarta2).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("1.2\nkm", R.drawable.petrolbank))));
//        mMap.addMarker(new MarkerOptions().position(southjakarta3).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("500\nmm", R.drawable.petrolbank_01))));
        mMap.addMarker(new MarkerOptions().position(southjakarta4).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("400\nmm", R.drawable.petrolbank_01))));
        mMap.addMarker(new MarkerOptions().position(southjakarta5).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("300\nmm", R.drawable.petrolbank_02))));
        mMap.addMarker(new MarkerOptions().position(southjakarta6).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("200\nmm", R.drawable.petrolbank))));
        mMap.addMarker(new MarkerOptions().position(southjakarta7).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("100\nmm", R.drawable.petrolbank_01))));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 15f));


    }

}