package com.gasaver.activity;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.BannersResponse;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvancedBannerSlidSearchActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3;

    private ViewPager viewPager;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 500;
    private final long PERIOD_MS = 3000;

    BannersResponse bannersResponse;
    RecyclerView recyclerview_Company_Logos;
    private int[] imageIds = new int[]{
            R.drawable.sample1,
            R.drawable.sample2,
            R.drawable.sample3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_banner_slid_search);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Promotions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerview_Company_Logos = findViewById(R.id.recyclerview_Company_Logos);
        ArrayList<String> imageUrlList = new ArrayList<>();
        imageUrlList.add("https://cdn.pixabay.com/photo/2023/03/22/11/07/seeds-7869190_960_720.jpg");
        imageUrlList.add("https://cdn.pixabay.com/photo/2022/05/11/22/17/pink-hibiscus-7190314_960_720.jpg");
        imageUrlList.add("https://cdn.pixabay.com/photo/2023/03/16/08/51/flowers-7856225_960_720.jpg");
// Add more image URLs as needed

        recyclerview_Company_Logos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerview_Company_Logos.setAdapter(new recyclerview_Company_Logos_Adaptor(this,imageUrlList));
        viewPager = findViewById(R.id.view_pager);

        // Create an array of image URLs
        String[] imageUrls = {"https://cdn.pixabay.com/photo/2023/03/22/11/07/seeds-7869190_960_720.jpg", "https://cdn.pixabay.com/photo/2022/05/11/22/17/pink-hibiscus-7190314_960_720.jpg", "https://cdn.pixabay.com/photo/2023/03/16/08/51/flowers-7856225_960_720.jpg"};

        // Set the adapter to the ViewPager
        viewPager.setAdapter(new ImageAdapter(this, imageUrls));


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELAY_MS, PERIOD_MS);


        fetchBanners();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }


    private void fetchBanners() {
        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);
        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", "2168");
        jsonObject.addProperty("token", "51da1d874ab9626e5f851d02fa31472259d759ae508c988f38184582c0433fb1");

        Call<BannersResponse> call = apiInterface.fetchBanners(jsonObject);

        call.enqueue(new Callback<BannersResponse>() {

            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {

                bannersResponse = response.body();
                CommonUtils.hideLoading();
                ArrayList<String> logoList = new ArrayList<>();

                for (int i = 0; i < bannersResponse.getCompanyDetails().size(); i++) {
                    String logoUrl = bannersResponse.getCompanyBasePath() + bannersResponse.getCompanyDetails().get(i).getLogo();
                    bannersResponse.getCompanyDetails().get(i).getId();
                    // Add the logo URL to the list only if it is not null or empty and its size is greater than 0
                    if (!logoUrl.isEmpty()) {
                        logoUrl.length();
                        logoList.add(logoUrl);
                    }
                }

// Set the adapter with the logo list
                recyclerview_Company_Logos.setLayoutManager(new LinearLayoutManager(AdvancedBannerSlidSearchActivity.this.getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerview_Company_Logos.setAdapter(new recyclerview_Company_Logos_Adaptor(AdvancedBannerSlidSearchActivity.this,logoList));


            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });
    }


    private static class recyclerview_Company_Logos_Adaptor extends RecyclerView.Adapter<recyclerview_Company_Logos_ViewHolder> {
        private List<String> imageUrls;
        private Activity activity;

        public recyclerview_Company_Logos_Adaptor(Activity activity ,List<String> imageUrls) {
            this.imageUrls = imageUrls;
            this.activity = activity;
        }

        @NonNull
        @Override
        public recyclerview_Company_Logos_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_company_logo_profile, parent, false);
            return new recyclerview_Company_Logos_ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull recyclerview_Company_Logos_ViewHolder holder, int position) {
            String imageUrl = imageUrls.get(position);
            // Load the image from the URL and set it in the ImageView using Picasso or Glide
            Glide.with(activity).load(imageUrl).circleCrop().into(holder.companyLogoImageView);
        }

        @Override
        public int getItemCount() {
            return imageUrls.size();
        }
    }

    private static class recyclerview_Company_Logos_ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView companyLogoImageView;

        public recyclerview_Company_Logos_ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLogoImageView = itemView.findViewById(R.id.iv_profile_img);
        }
    }
    private static class ImageAdapter extends PagerAdapter {
        private final Context context;
        private final String[] imageUrls;

        public ImageAdapter(Context context, String[] imageUrls) {
            this.context = context;
            this.imageUrls = imageUrls;
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(imageUrls[position]).into(imageView);
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }

}



