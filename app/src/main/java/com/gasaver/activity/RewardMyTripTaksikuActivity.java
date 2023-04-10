package com.gasaver.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

//public class RewardMyTripTaksikuActivity extends AppCompatActivity {
public class RewardMyTripTaksikuActivity extends BaseActivity implements OnMapReadyCallback {
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_my_trip_taksiku);


//        setTitle(R.id.inc_toolbar_reward,R.id.tv_title_reward, "MY TRIP", R.drawable.ic_back_white, R.color.purple, R.color.white);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_reward);
        mapFragment.getMapAsync(this);
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

//        mMap.addMarker(new MarkerOptions().position(jakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Set Pickup Location", R.drawable.dot_pickup))));
        mMap.addMarker(new MarkerOptions().position(jakarta).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView(" ", R.drawable.dot_pickup))));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 15f));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}