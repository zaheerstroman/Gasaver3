package com.gasaver.fragment;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.gasaver.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//import com.e.gasserviceapp.fragment.databinding.FragmentBottomHome2Binding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BottomHomeFragment extends Fragment {

    View view;
    CardView cardCallRecording, cardFiles, cardSearch;
    ViewPager viewPager;
    Button exploreButton, search_imgrje;
    public Context context, mContext;

    Spinner spinner_caseText1, registertype_spinner;
    private RecyclerView courseRV;
    public RecyclerView recyclerView1;

//    String[] country = { "India", "USA", "China", "Japan", "Other"};


    ImageView working1, edit_icon, search_imggdsgdsg;
    private int currentPage = 0; // this will tell us the current page available on the view pager
    private boolean isCountDownTimerActive = false; // let the timer start if and only if it has completed previous task
    private Handler handler;
    private static final long SLIDER_TIMER = 2000; // change slider interval

    public BottomHomeFragment() {
        // Required empty public constructor
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        view = inflater.inflate(R.layout.fragment_home, null);
//        view = inflater.inflate(R.layout.fragment_bottom_home, null);
//
//
//        NavigationView navigationView = view.findViewById(R.id.navigation_view);
//
//        return view;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fitness_centers, container, false);


//        spinner_caseText1 = (Spinner) view.findViewById(R.id.spinner_caseText1);
//        String[] spinnerItems = {"January 2022","February 2022","March 2022","April 2022","May 2022","June 2022","July 2022","August 2022","September 2022","October 2022","November 2022","December 2022"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerItems);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_caseText1.setAdapter(adapter);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()


//                        .target(new LatLng(17.393522,78.472950))
                        .target(new LatLng(17.43539564025845, 78.44543771341806))

                        .zoom(10).bearing(0).tilt(45).build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);


                mMap.addMarker(new MarkerOptions().position(new LatLng(17.438226164864947, 78.44320559487082)).title("SUPER GAS AUTO LPG STATION AMEERPET, 8-3-217, Srinivas Nagar Ameerpet(West Hyderabad Town Survey No. 6/1, Ward A, Block 5, Telangana 500038"));
                //.icon(bitmapDescriptorFromVector(getActivity(),R.drawable.spider)));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.43790154937477, 78.44273280167631)).title("BP-AMEERPET BPCL COMPANY RETAIL OUTLET, 8-3-217, BPCL Retail Outlet, Srvs Nagar Colony, Srinagar Colony Main Rd, Srinagar Colony, Hyderabad, Telangana 500038"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.427572819468452, 78.45143405308845)).title("Indian Oil Petrol Pump, Nagarjuna Cir Rd, Mothi Nagar, Dwarakapuri, Banjara Hills, Hyderabad, Telangana 500082").snippet("His Talent : Plenty of money"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.428790128563907, 78.43954284464363)).title("Rameshwar Filling Station - IOCL Retail Outlet, 8-2-268/A/1/2, Rd No 2,, Road No. 2, Sri Nagar Colony, Aurora Colony, Banjara Hills, Hyderabad, Telangana 500873"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.431398795025764, 78.43195843670263)).title("Metro Fuel Point - IOCL Retail Outlet, 833, #8-3, 233&234, Kamalapuri Gas Station Road, Sri Nagar Colony, Hyderabad, Telangana 500045"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.43491998911282, 78.43015599236239)).title("Hindustan Petroleum Corporation Limited, Survey No, 123, Yousufguda Police Lines, Sri Krishna Nagar, Yousufguda, Hyderabad, Telangana 500045"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.432299572074314, 78.4227745536356)).title("Hindustan Petroleum Corporation Limited, Old Survey No 403/1, New Survey No 120 Jubilee Hills CHS, Road No 5, Shaikpet, Hyderabad, Telangana 500033"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.43078974437147, 78.41826975129736)).title("Indian Oil, Plot No 1354, Road No. 1, & 68, Jubilee Hills, Hyderabad, Telangana 500033"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.425630645677938, 78.41208994213076)).title("Indian Oil, Plot No 1354, Road No. 1, & 68, Jubilee Hills, Hyderabad, Telangana 500033"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.425631735067814, 78.45937895477593)).title("Indian Oil, H No 6/3/927/C & D, Raj Bhavan Rd, Somajiguda, Hyderabad, Telangana 500082"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.429916672644104, 78.45713340468335)).title("Hindustan Petroleum Corporation Limited, D No, 6/3/1106, Raj Bhavan Rd, Nishat Bagh Colony, Somajiguda, Hyderabad, Telangana 500082"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.432448633924427, 78.45560234780203)).title("Indian Oil, No 6 & 3 & 873 Somajiguda, Ameerpet, Hyderabad, Telangana 500016"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.459351519073444, 78.43467688505594)).title("Indian Oil, D No, 7/2/1816, Erragadda Main Rd, Sanath Nagar, Hyderabad, Telangana 500018"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.4501200187884, 78.44969965547091)).title("S G S S Enterprises Indian oil petrol bunk, 7-1, 276/2, Balkampet Rd, Neemkar Nagar, Jaya Prakash Nagar, Balkampet, Hyderabad, Telangana 500018"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.45030808245249, 78.450199571975)).title("Indian Oil, Ground Floor, Suprabath Nagar Colony, Balkampet, Hyderabad, Telangana 500016, 09440422338"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.445518008242086, 78.46165796813808)).title("Indian Oil, Ground Floor, Begumpet, Hyderabad, Telangana 500016, 09246531399"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.438212, 78.367566)).title("Indian Oil Petrol Pump (Company Owned Company Operated COCO), A, Plot No1-11-258, 29, Begumpet Rd, Prakash Nagar, Hyderabad, Telangana 500016, 04027768685"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.560328372467396, 78.49536301906204)).title("Indubala Fuel Needs, HF3V+CCM, Unnamed Road, Laxmi Nagar Colony, Gundlapochampalli, Secunderabad, Telangana 500014, "));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.551323792489605, 78.47438396114599)).title("Bharat Petroleum, Petrol Pump -Nikhil Fuel Station, Dulapally, Hyderabad, Telangana 500074, 09347546647"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.522904475630348, 78.47969162500118)).title("Bhagyanagar Gas Limited CNG Station, Medchal Rd, Petbasheerabad, Dandamudi Enclave, Jeedimetla, Hyderabad, Telangana 500067, 18002333555"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.517843034268836, 78.46540176077565)).title("Bharat Petroleum Corporation ltd, Survey No. 236, Suchitra, Junction, Quthbullapur Main Rd, Quthbullapur, Hyderabad, Telangana 500055, 09885911072"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.51161337423542, 78.50949619895731)).title("Eco Energy Fuels, H.NO:8-75, Chatanpally Rd, Ram Nagar Colony, Telangana 509216"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.49136550344982, 78.53399310905823)).title("Goshamahal Service Station Bharat Petroleum, FGJM+7R5, RK Puram Rd, Sri colony, Neredmet, Secunderabad, Telangana 500056, 1800224344"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.4609894695474, 78.51317073547244)).title("Hindustan Petroleum Corporation Limited, Survey No 73, Plot No 21,53 & 54 Secunderabad Cantonment, East Marredpally, Hyderabad, Telangana 500026, 09440834923"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.524851146258737, 78.47846677949615)).title("Bhagyanagar Gas Limited CNG Station, Medchal Rd, Petbasheerabad, Dandamudi Enclave, Jeedimetla, Hyderabad, Telangana 500067, 18002333555"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.516285639300584, 78.46621832444569)).title("Bharat Petroleum Corporation ltd, Survey No. 236, Suchitra, Junction, Quthbullapur Main Rd, Quthbullapur, Hyderabad, Telangana 500055, 09885911072"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.477335658125874, 78.41202139944807)).title("Shiva Shakti Fuel Station - BPCL Retail Outlet, Survey No 1011, 10, Moosapet Rd, Kukatpally, Hyderabad, Telangana 500018, 09885900667"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.45513657475918, 78.40058950806763)).title("BOOK MY FUEL - Doorstep Diesel Delivery, 73p, Beverly Hills, Guttala_Begumpet, Kavuri Hills, Jubilee Hills, Hyderabad, Telangana 500033. 09169484848"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.47889338689438, 78.41487937229316)).title("Shiva Shakti Fuel Station - BPCL Retail Outlet, Survey No 1011, 10, Moosapet Rd, Kukatpally, Hyderabad, Telangana 500018, 09885900667"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.50654085239905, 78.38711620751211)).title("Indian Oil Fuel Station (Ganesh Service Station), Service Rd, Dharma Reddy Colony Phase II, Kukatpally Housing Board Colony, Kukatpally, Hyderabad, Telangana 500085, 08857242701"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.51122310525919, 78.3502330757176)).title("BPCL Diesel Pump, F8VX+GHF, Indra Reddy Allwyn Colony, Hafeezpet, Hyderabad, Telangana 500049"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.481472196998137, 78.31975895084517)).title("L Fuel HPCL, F8H9+GRH, Nallagandla Bypass Rd, Gulmohar Park Colony, Serilingampalle (M), Telangana 500019"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.48783980661841, 78.31939480209783)).title("Venkat Sai Fuels, F8P9+5WP, Lingampally Rd, Sivaji Nagar, Chanda Nagar, Serilingampalle (M), Telangana 500019"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.498041976471974, 78.31665832672361)).title("Mallikarjuna Fuel Station, NH65, Gouthami Nagar Colony, Jawahar Colony, Chanda Nagar, Hyderabad, Telangana 500050"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.499106141173762, 78.3150275437491)).title("Krishna Fuel Station - IOCL Retail Outlet, F8W8+Q38, near Bajaj Electronics, Lingampally, Bharat Heavy Electricals Limited, Hyderabad, Telangana 500032"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.395505546655734, 78.4308513315793)).title("Indian Oil, No 12/2, 279, A/B, Rethibowli, Mehdipatnam, Hyderabad, Telangana 500008, 09849815219"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.382337058526485, 78.43881355880269)).title("Hindustan Petroleum Corporation Limited, H No 13/6/457/5, 6, 7, Karwan Rd, Gudimalkapur, Hyderabad, Telangana 500006, 09000000400"));

                mMap.addMarker(new MarkerOptions().position(new LatLng(17.41998515510161, 78.40998747455713)).title("Indian Oil, Road No 1, Sy No 120 & 102/1, Block No 2 Ward No 8, Film Nagar, Shaikpet Rd, Hakimpet, Hyderabad, Telangana 500033, 07702003513"));


            }
        });
        return rootView;

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
////        inflater.inflate(R.menu.search_option_menu, menu);
//        inflater.inflate(R.menu.search_menu, menu);
////        MenuItem item = menu.findItem(R.id.action_search);
//        MenuItem item = menu.findItem(R.id.actionSearch);
//
//        SearchView searchView = new SearchView(((MainActivity) mContext).getSupportActionBar().getThemedContext());
//        // MenuItemCompat.setShowAsAction(item, //MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | //MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//        //  MenuItemCompat.setActionView(item, searchView);
//        // These lines are deprecated in API 26 use instead
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        item.setActionView(searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }

//    //Performing action onItemSelected and onNothing selected
//    @Override
//    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getContext(),country[position] , Toast.LENGTH_LONG).show();
//    }
//    @Override
//    public void onNothingSelected(AdapterView<?> arg0) {
//        // TODO Auto-generated method stub
//    }
//}


}