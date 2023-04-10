package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gasaver.R;

public class MainActivityGasWithoutFirebase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gas_without_firebase);
    }

//    private CircleImageView imgHeader;
//    private TextView txtName, txtEmail;
//
//    private NavDrawerLayoutBinding navDrawerLayoutGasBinding;
//    private ActivityMainGasBinding activityMainGasBinding;
//    private ToolbarLayoutBinding toolbarLayoutGasBinding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        navDrawerLayoutGasBinding = NavDrawerLayoutBinding.inflate(getLayoutInflater());
//        setContentView(navDrawerLayoutGasBinding.getRoot());
//        activityMainGasBinding = navDrawerLayoutGasBinding.mainActivityGas;
//        toolbarLayoutGasBinding = activityMainGasBinding.toolbar;
//        setSupportActionBar(toolbarLayoutGasBinding.toolbar);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this,
////                navDrawerLayoutBinding.navDrawer,
//                navDrawerLayoutGasBinding.navDrawer,
//
////                toolbarLayoutBinding.toolbar,
//                toolbarLayoutGasBinding.toolbar,
//                R.string.open_navigation_drawer,
//                R.string.close_navigation_drawer
//        );
//
//        navDrawerLayoutGasBinding.navDrawer.addDrawerListener(toggle);
//
//
//        toggle.syncState();
//
//        NavController navController = Navigation.findNavController(this, R.id.fragmentContainer);
//        NavigationUI.setupWithNavController(
//
////                navDrawerLayoutBinding.navigationView,
//                navDrawerLayoutGasBinding.navigationView,
//                navController
//        );
//
//        View headerLayout = navDrawerLayoutGasBinding.navigationView.getHeaderView(0);
//
//
//        imgHeader = headerLayout.findViewById(R.id.imgHeader);
//        txtName = headerLayout.findViewById(R.id.txtHeaderName);
//        txtEmail = headerLayout.findViewById(R.id.txtHeaderEmail);
//
////        getUserData();
//
//
//    }
//
//    @Override
//    public void onBackPressed() {
//
////        if (navDrawerLayoutBinding.navDrawer.isDrawerOpen(GravityCompat.START))
////            navDrawerLayoutBinding.navDrawer.closeDrawer(GravityCompat.START);
//        if (navDrawerLayoutGasBinding.navDrawer.isDrawerOpen(GravityCompat.START))
//            navDrawerLayoutGasBinding.navDrawer.closeDrawer(GravityCompat.START);
//        else
//            super.onBackPressed();
//    }
}