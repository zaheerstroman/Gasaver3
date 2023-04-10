package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.gasaver.R;

public class SplashScreenActivity extends AppCompatActivity {

    /*Duration of wait*/
    int SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen1);

        /*New Handler to start the Menu-Activity and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                //Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);

//                Intent mainIntent = new Intent(SplashScreenActivity.this, IntroActivity.class);
//                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
//                Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
//                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)this).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)this).getSupportActionBar().show();
    }
}

