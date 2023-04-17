package com.gasaver.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.gasaver.R;
import com.gasaver.fragment.HomeFragmentGasaver;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    AlertDialog alertDialog;

//    public List<Item> itemList;
//    public ItemAdapter itemAdapter;
//    private RecyclerView courseRV;
    public RecyclerView courseRV;
    public RecyclerView recyclerView;
    public RecyclerView recyclerView1;

//    public CourseAdapter adapter;
//    public ArrayList<CourseModel> courseModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = new Bundle();

        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main1);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        //setSupportActionBar(toolbar);

//        getSupportActionBar().setIcon(R.mipmap.facebook);
//        getSupportActionBar().setIcon(R.mipmap.principle_insolvency_icons_05);

//        recyclerView1 = findViewById(R.id.recyclerView1);



//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();


//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display menu1 when the activity is loaded
//        displaySelectedScreen(R.id.nav_home);
        displaySelectedScreen(R.id.nav_menu1);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }




//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.item_search:
//                Toast.makeText(getApplicationContext(), "Start Selected", Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.item_setting:
//                //Toast.makeText(getApplicationContext(), "Search Selected", Toast.LENGTH_LONG).show();
//                Intent as = new Intent(MainActivity.this, SetProfile.class);
//                startActivity(as);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void displaySelectedScreen(int itemId) {
        //creating fragment object
        Fragment fragment = null;
        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_menu1:
                fragment = new HomeFragmentGasaver();
//                fragment = new FragmentHome();
//                fragment = new ProfileFragment();

//                toolbar.setTitle("Home");
                toolbar.setTitle("");
                break;
//            case R.id.nav_menu2:
//                fragment = new FragmentFitnessCenters();
//                toolbar.setTitle("Fitness Centers");
//                break;
//            case R.id.nav_menu3:
//                fragment = new FragmentFeedback();
//                toolbar.setTitle("Feedback");
//                break;
//            case R.id.nav_menu4:
//                fragment = new FragmentApps();
//                toolbar.setTitle("Apps");
//                break;
//            case R.id.nav_menu5:
//                fragment = new FragmentShare();
//                toolbar.setTitle("Share");
//                break;
//            case R.id.nav_menu6:
//                fragment = new FragmentLogout();
//                toolbar.setTitle("Logout");
//                finish();
//                break;
        }
        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer);
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //calling the method display selected screen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//        // AlertDialog Builder class
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        // Set the message show for the Alert time
//        builder.setMessage("Do you want to exit ?");
//        // Set Alert Title
//        builder.setTitle("Alert !");
//        builder.setCancelable(false);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        // Create the Alert dialog
//        alertDialog = builder.create();
//        // Show the Alert Dialog box
//        alertDialog.show();
//    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        ((AppCompatActivity) this).getSupportActionBar().hide();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity) this).getSupportActionBar().show();
//    }
}
