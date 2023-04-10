package com.gasaver.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.BannersResponse;
import com.gasaver.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    // Context object
    Context context;

//    ArrayList<BannersResponse.Banner> banners;
    ArrayList<BannersResponse.AddsDetail> banners;
//    ArrayList<BannersResponse> banners;


    //    ArrayList<BannersResponse.AddsDetail> images;
    ArrayList<BannersResponse.AddsDetail> images;
//    ArrayList<String> images;


    // Layout Inflater
    LayoutInflater mLayoutInflater;
    boolean fromPropertyDetails = false;


    // Viewpager Constructor
//    public ViewPagerAdapter(Context context, ArrayList<BannersResponse.Banner> images) {
//    public ViewPagerAdapter(Context context, ArrayList<BannersResponse.AddsDetail> images) {
//    public ViewPagerAdapter(Context context, ArrayList<BannersResponse> images) {
//    public ViewPagerAdapter(Context context, ArrayList<BannersResponse.AddsDetail> images) {
//
//
//        this.context = context;
//        this.banners = images;
//        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    // Viewpager Constructor
//    public ViewPagerAdapter(Context context, ArrayList<String> images, boolean fromPropertyDetails) {
//        this.context = context;
//        this.images = images;
//        this.fromPropertyDetails = fromPropertyDetails;
//        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    //    public ViewPagerAdapter(Context applicationContext, String addsDetails) {


    //TPROPERTY :- HomeFragment:----(Banner):-----------------------------------------------------
    public ViewPagerAdapter(Context context, ArrayList<BannersResponse.AddsDetail> images) {
//    public ViewPagerAdapter(Context applicationContext, ArrayList<BannersResponse.AddsDetail> images) {
//    public ViewPagerAdapter(Context applicationContext, ArrayList<BannersResponse.AddsDetail> addsDetails) {
//    public ViewPagerAdapter(Context applicationContext, ArrayList<BannersResponse.AddsDetail> addsDetails) {
        this.context = context;
//        this.context = applicationContext;
//        this.images = images;
        this.banners = images;
//        this.images = addsDetails;
//        this.banners = addsDetails;

        this.fromPropertyDetails = fromPropertyDetails;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    //TPROPERTY :- PropertyDetailsActivity:----(Banner):-------------
    // Viewpager Constructor
    public ViewPagerAdapter(Context context, ArrayList<BannersResponse.AddsDetail> images, boolean fromPropertyDetails) {
//    public ViewPagerAdapter(Context context, ArrayList<String> images, boolean fromPropertyDetails) {
        this.context = context;
//        this.images = images;
        this.images = images;

        this.fromPropertyDetails = fromPropertyDetails;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //GraphActivityGeek
//    public ViewPagerAdapter(Context applicationContext, String graphReport) {
//        this.context = applicationContext;
//        this.images = images;
//
//    }

//    public ViewPagerAdapter(Context applicationContext, String graphReport) {
//
//    }

    @Override
    public int getCount() {
        if (fromPropertyDetails)
            return images.size();
        // return the number of images
        return banners.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.image_item, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);

        // setting the image in the imageView
        if (fromPropertyDetails)
//            Glide.with(context).load(Constants.PROPERTY_IMAGE_URL + images.get(position)).into(imageView);
//            Glide.with(context).load(Constants.BANNER_IMAGE_URL + images.get(position)).into(imageView);
            Glide.with(context).load(Constants.BANNER_IMAGE_URL + images.get(position)).into(imageView);

            //ProfileFragment
//        Glide.with(getActivity()).load(response.body().getBase_path() + response.body().getData().getProfilePhoto())
//                .error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfileImg);
        else
            Glide.with(context).load(Constants.BANNER_IMAGE_URL + banners.get(position).getAttachment()).into(imageView);
//            Glide.with(context).load(Constants.BANNER_IMAGE_URL + banners.get(position).getBasePath()).into(imageView);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (fromPropertyDetails)
//                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.PROPERTY_IMAGE_URL + images.get(position))));
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.BANNER_IMAGE_URL + images.get(position))));

//                    else
//                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(banners.get(position).getUrl())));
//                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(banners.get(position).getBasePath())));

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }


}
