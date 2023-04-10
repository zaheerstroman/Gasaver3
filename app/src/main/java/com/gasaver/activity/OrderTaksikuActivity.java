package com.gasaver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gasaver.R;

//public class OrderTaksikuActivity extends BaseActivity {
public class OrderTaksikuActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order_taksiku);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order);
        setContentView(R.layout.activity_order_taksiku);

        setTitle(R.id.inc_toolbar,R.id.tv_title, "ORDER", R.drawable.ic_back_white, R.color.purple, R.color.white);

        Button select = (Button) findViewById(R.id.bt_order);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderTaksikuActivity.this, MyTripTaksikuActivity.class));
            }
        });
    }

//    private void setTitle(int inc_toolbar, int tv_title, String order, int ic_back_white, int purple, int white) {
//
//    }

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