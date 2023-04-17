package com.gasaver.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;

//public class DashBoardGeeksActivity extends AppCompatActivity {
public class DashBoardGeeksActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btn_request;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dash_board_geeks);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dash_board);
                setContentView(R.layout.activity_dash_board_geeks);

        setTitleHome(R.id.toolbar_taksiku, R.id.iv_title, R.drawable.ic_burger, R.drawable.gas_saver_logo_rectangular_min);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_request = (Button) findViewById(R.id.bt_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(Home.this, SelectDriver.class));
//                startActivity(new Intent(HomeTaksikuActivity.this, SelectDriverTaksikuActivity.class));
//                startActivity(new Intent(DashBoardGeeksActivity.this, OrderTaksikuActivity.class));
                startActivity(new Intent(DashBoardGeeksActivity.this, MainActivityGas.class));

            }
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation_geeks);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
//                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        startActivity(new Intent(getApplicationContext(),MainActivityGas.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboard:
                        return true;
                    case R.id.about:
//                        startActivity(new Intent(getApplicationContext(),About.class));
//                        startActivity(new Intent(getApplicationContext(),AboutGeeksActivity.class));
                        startActivity(new Intent(getApplicationContext(),MainActivityGas.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public Bitmap getBitmapFromView(String title, int dotBg) {

        LinearLayout llmarker = (LinearLayout) findViewById(R.id.ll_marker);
        TextView markerImageView = (TextView) findViewById(R.id.tv_title);
        markerImageView.setText(title);
        View dot = (View) findViewById(R.id.dot_marker);
        dot.setBackgroundResource(dotBg);

        llmarker.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(llmarker.getMeasuredWidth(), llmarker.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        llmarker.layout(0, 0, llmarker.getMeasuredWidth(), llmarker.getMeasuredHeight());
        llmarker.draw(canvas);
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

        mMap.addMarker(new MarkerOptions().position(jakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Current Location", R.drawable.dot_pickup))));
        mMap.addMarker(new MarkerOptions().position(southjakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Go Location", R.drawable.dot_dropoff))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 15f));


    }
}