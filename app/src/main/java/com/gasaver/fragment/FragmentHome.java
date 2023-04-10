package com.gasaver.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
import android.view.View;
import android.view.ViewGroup;

import com.gasaver.R;
//import com.e.gasserviceapp.databinding.FragmentHome2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//import com.e.gasserviceapp.fragment.databinding.FragmentHome2Binding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FragmentHome extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{

    View view;
    BottomNavigationView navigation;

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        view = inflater.inflate(R.layout.fragment_home, container, false);
//        view = inflater.inflate(R.layout.fragment_home1, container, true);
        view = inflater.inflate(R.layout.fragment_home1, container, false);




//        navigation = view.findViewById(R.id.navigation1);
//        navigation = view.findViewById(R.id.bottomAppBar);
        navigation = view.findViewById(R.id.bottomNavigationView);


        navigation.setOnNavigationItemSelectedListener(this);
        FragmentTransaction tx = getFragmentManager().beginTransaction();
//        tx.replace(R.id.fragment_container, new BottomHomeFragment());
        tx.replace(R.id.fragment_container1, new BottomHomeFragment());
//        tx.replace(R.id.bottomAppBar, new BottomHomeFragment());
//        bottomNavigationView.background = null;
//        bottomNavigationView.menu.getItem(2).isEnabled = false;
        tx.commit();
        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
//            3
//            case R.id.navigation_home:
//            4
            case R.id.navigation_home1:
                fragment = new BottomHomeFragment();
                break;
            case R.id.navigation_dashboard1:
                fragment = new BottomFilesFragment();
                break;
            case R.id.navigation_notifications1:
//                fragment = new BottomSearchFragment();
                fragment = new BottomCalenderDateFragment();
                break;

            case R.id.navigation_profile1:
//                fragment = new BottomMyAccountFragment();
                fragment = new ProfileFragment();

                break;
//
//            case R.id.navigation_notifications1:
//                fragment = new BottomMyAccountFragment();
//                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getFragmentManager()
                    .beginTransaction()
//                    .replace(R.id.fragment_container, fragment)
                    .replace(R.id.fragment_container1, fragment)


                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }



    public boolean onBackPressed() {
        return false;
    }

}