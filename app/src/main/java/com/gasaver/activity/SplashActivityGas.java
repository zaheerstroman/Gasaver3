package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.gasaver.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivityGas extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_gas);
        firebaseAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firebaseAuth.getCurrentUser() != null) {
//                    startActivity(new Intent(SplashActivityGas.this, MainActivity.class));
//                    startActivity(new Intent(SplashActivityGas.this, MainActivityGas.class));

//                    startActivity(new Intent(SplashActivityGas.this, HomeTaksikuActivity.class));
//                    startActivity(new Intent(SplashActivityGas.this, MainActivityGasWithoutFirebase.class));

//                    startActivity(new Intent(SplashActivityGas.this, LoginActivityGas.class));
//                    startActivity(new Intent(SplashActivityGas.this, LoginActivity.class));
                    startActivity(new Intent(SplashActivityGas.this, UserSignInActivity.class));


                    finish();
                } else {
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                    startActivity(new Intent(SplashActivityGas.this, LoginActivity.class));
                    startActivity(new Intent(SplashActivityGas.this, GettingStarted.class));
//                    startActivity(new Intent(SplashActivityGas.this, MainActivity.class));

//                    startActivity(new Intent(SplashActivityGas.this, LoginActivityGas.class));
//                    startActivity(new Intent(SplashActivityGas.this, MainActivityGas.class));

//                    startActivity(new Intent(SplashActivityGas.this, MainActivityGasWithoutFirebase.class));

//                    startActivity(new Intent(SplashActivityGas.this, MainActivityGas.class));



                    finish();
                }


            }
        }, 3000);


    }

}