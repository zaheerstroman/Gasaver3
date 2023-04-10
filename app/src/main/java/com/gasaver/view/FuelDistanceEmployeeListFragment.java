package com.gasaver.view;

import static com.facebook.FacebookSdk.getApplicationContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.StationDataResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.gasaver.activity.DirectionActivity;
import com.gasaver.activity.MainPickmanActivity;
import com.gasaver.databinding.ActivityFuelDistanceEmployeeListActivityityBinding;
import com.gasaver.network.APIClient;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonObject;
import com.google.maps.android.SphericalUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FuelDistanceEmployeeListFragment extends BottomSheetDialogFragment {

    ActivityFuelDistanceEmployeeListActivityityBinding binding;
    ArrayList<StationDataResponse.StationDataModel> stationDataList;
    Location cuLocation;

    public FuelDistanceEmployeeListFragment(ArrayList<StationDataResponse.StationDataModel> stationDataList, Location currentLocation) {
        this.stationDataList = stationDataList;
        this.cuLocation = currentLocation;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = ActivityFuelDistanceEmployeeListActivityityBinding.inflate(getLayoutInflater());

        if (stationDataList == null || stationDataList.isEmpty()) {
            Toast.makeText(getActivity(), "no stations found", Toast.LENGTH_SHORT).show();
        } else
            binding.recyEmployeelist.setAdapter(new FuelDistanceEmployeeListAdapter(getActivity(), stationDataList));
        return binding.getRoot();
    }


    public class FuelDistanceEmployeeListAdapter extends RecyclerView.Adapter<FuelDistanceEmployeeListAdapter.ViewHolder> {

        List<StationDataResponse.StationDataModel> stationDataList = new ArrayList<>();

        Context context;


        public FuelDistanceEmployeeListAdapter(Context context, List<StationDataResponse.StationDataModel> stationDataList) {
            this.stationDataList = stationDataList;
            this.context = context;
        }

        @NonNull
        @Override
        public FuelDistanceEmployeeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fueldistanceemployeelistlayout, parent, false);
            return new FuelDistanceEmployeeListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FuelDistanceEmployeeListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            try {
                StationDataResponse.StationDataModel stationDataModel = stationDataList.get(position);
                holder.iv_wishlist1.setImageResource(stationDataModel.getWishlist() != null && stationDataModel.getWishlist().equalsIgnoreCase("yes") ? R.drawable.wishlist_added : R.drawable.like_icon);
                holder.iv_wishlist1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveWishlist(stationDataModel, stationDataModel.getId(), stationDataModel.getWishlist() != null && stationDataModel.getWishlist().equalsIgnoreCase("Yes") ? "No" : "Yes", holder.iv_wishlist1);
//
//                        saveWishlist(wishlistModel, wishlistModel.getId(), stationDataModel.getWishlist() != null && stationDataModel.getWishlist().equalsIgnoreCase("Yes") ? "No" : "Yes");
//                        saveWishlist(wishlistModel, wishlistModel.getId(), wishlistModel.getWishlist() != null && wishlistModel.getWishlist().equalsIgnoreCase("Yes") ? "No" : "Yes");

                    }
                });

                List<StationDataResponse.PriceModel> prices = stationDataModel.getPrices();
                Glide.with(getActivity()).load(stationDataList.get(position).getBrandIcon()).into(holder.iv_proj_img);
//                stationDataList
                holder.tv_name.setText(stationDataModel.getName());


                holder.tv_city.setText(stationDataModel.getCity());

                holder.tv_addr.setText(stationDataModel.getAddress().toLowerCase(Locale.ROOT));

                holder.tv_addr.setText(stationDataModel.getAddress());
                holder.tv_city.setText(stationDataModel.getBrand());
                String latesttDTE = null;
                try {
                    holder.tv_price.setText("");
                    for (StationDataResponse.PriceModel priceModel :
                            stationDataModel.getPrices()) {
                        holder.tv_price.append(priceModel.getType() + ": " + priceModel.getAmount() + " | ");
                        if (latesttDTE==null)
                            latesttDTE = priceModel.getLastupdated();
                        else if (CommonUtils.getDate(latesttDTE).getTime() >= (CommonUtils.getDate(priceModel.getLastupdated()).getTime())) {
                            latesttDTE = priceModel.getLastupdated();
                        }
                    }
                    holder.tv_lastupdated.append(latesttDTE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    double distance = SphericalUtil.computeDistanceBetween(new LatLng(cuLocation.getLatitude(), cuLocation.getLongitude()), new LatLng(Double.parseDouble(stationDataModel.getLatitude()), Double.parseDouble(stationDataModel.getLongitude())));

                    if (distance > 1000) {
                        double kilometers = distance / 1000;
                        holder.tv_dis.setText(CommonUtils.roundUpDecimal(distance, 2) + " KM");
                    } else {
                        holder.tv_dis.setText(CommonUtils.roundUpDecimal(distance, 2) + " Meters");
                    }
                    float speed = cuLocation.getSpeed();

                    if (speed > 0) {
                        double time = distance / speed;
                        holder.tv_dis.setText(CommonUtils.roundUpDecimal(time, 2) + " sec");
                    } else {
                        holder.tv_dis.setText("N/A");

                    }
//                    binding.stationLayout.txtDis.setText(String.valueOf(stationDataModel.getDistance()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                holder.btn_navigate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Findroutes(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), marker.getPosition());
                        {

                            Intent intent = new Intent(requireContext(), DirectionActivity.class);
                            intent.putExtra("lat", stationDataModel.getLatitude());
                            intent.putExtra("lng", stationDataModel.getLongitude());

                            startActivity(intent);

                        }
                    }
                });
                holder.btn_submit_prices.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), MainPickmanActivity.class);
                        intent.putExtra("station_id", stationDataModel.getStationid());
                        intent.putExtra("category", "3");
                        startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return stationDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_proj_img,iv_wishlist1;
            TextView tv_addr, tv_name,tv_city, tv_price, tv_dis,tv_lastupdated;
            LinearLayout layoutid;
            AppCompatButton btn_submit_prices, btn_navigate;
            LinearLayout ll_delete_my_prop;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_wishlist1 = itemView.findViewById(R.id.iv_wishlist1);
                iv_proj_img = itemView.findViewById(R.id.iv_proj_img);
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_lastupdated = itemView.findViewById(R.id.tv_lastupdated);
                ll_delete_my_prop = itemView.findViewById(R.id.ll_delete_my_prop);
                ll_delete_my_prop.setVisibility(View.GONE);
                btn_submit_prices = itemView.findViewById(R.id.btn_submit_prices);
                btn_navigate = itemView.findViewById(R.id.btn_navigate);
                tv_dis = itemView.findViewById(R.id.tv_dis);
                tv_price = itemView.findViewById(R.id.tv_price);
                tv_addr = itemView.findViewById(R.id.tv_addr);
                tv_city = itemView.findViewById(R.id.tvcity);
                layoutid = itemView.findViewById(R.id.layoutid);

            }
        }
    }

    private void saveWishlist(StationDataResponse.StationDataModel stationDataModel, Integer stationid, String iswishlist, ImageView ivWishlist) {
//private void saveWishlist(WishlistResponse.WishlistModel wishlistModel, Integer stationid, String iswishlist) {

        CommonUtils.showLoading(getActivity(), "Please Wait", false);
        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(getActivity()).getString(Constants.TOKEN));
        jsonObject.addProperty("vendor_id", stationid);
        jsonObject.addProperty("wishlist", iswishlist);

        Call<StationDataResponse> call = apiInterface.saveWishlist(jsonObject);
//    Call<WishlistResponse> call = apiInterface.saveWishlist(jsonObject);

        call.enqueue(new Callback<StationDataResponse>() {
//    call.enqueue(new Callback<WishlistResponse>() {

            @Override
            public void onResponse(Call<StationDataResponse> call, Response<StationDataResponse> response) {
//        public void onResponse(Call<WishlistResponse> call, Response<WishlistResponse> response) {

                try {
                    CommonUtils.hideLoading();
                    if (response.body().isStatus_code()) {

                        stationDataModel.setWishlist(iswishlist);
//                        wishlistModel.setWishlist(iswishlist);

                        ivWishlist.setImageResource(iswishlist.equalsIgnoreCase("yes") ? R.drawable.wishlist_added : R.drawable.like_icon);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<StationDataResponse> call, Throwable t) {
//            public void onFailure(Call<WishlistResponse> call, Throwable t) {

                CommonUtils.hideLoading();
            }
        });


    }
}