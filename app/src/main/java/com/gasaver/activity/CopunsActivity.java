package com.gasaver.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.CopunsResponse;
import com.gasaver.Response.GetRewardDataResponse;
import com.gasaver.Response.UploadDataResponse;
import com.gasaver.databinding.ActivityCopunsBinding;
import com.gasaver.databinding.ActivityUploadBinding;
import com.gasaver.databinding.CopunsListItemBinding;
import com.gasaver.databinding.UploadsListItemBinding;
import com.gasaver.network.APIClient;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CopunsActivity extends AppCompatActivity {

    ActivityCopunsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_copuns);
        binding = ActivityCopunsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getReportsData();
        getCopunsData();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

//        getSupportActionBar().setTitle("ViewAttachment");
//        getSupportActionBar().setTitle("Terms & Conditions");
        getSupportActionBar().setTitle("My Copuns");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });

    }

    //    private void getReportsData() {
    private void getCopunsData() {


        CommonUtils.showLoading(this, "Please Wait", false);
        com.gasaver.network.ApiInterface apiInterface = APIClient.getClient().create(com.gasaver.network.ApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", SharedPrefs.getInstance(this).getString(Constants.USER_ID));
        jsonObject.addProperty("token", SharedPrefs.getInstance(this).getString(Constants.TOKEN));

//        Call<UploadDataResponse> call = apiInterface.getRewardsData(jsonObject);
//        Call<GetRewardDataResponse> call = apiInterface.getRewardsData(jsonObject);
        Call<CopunsResponse> call = apiInterface.getCopunsData(jsonObject);

        call.enqueue(new Callback<CopunsResponse>() {
            @Override
            public void onResponse(Call<CopunsResponse> call, Response<CopunsResponse> response) {
                try {
                    CommonUtils.hideLoading();

//                    binding.recyclerviewCopunsList.setAdapter(new RewardActivity.UploadAdapter(CopunsActivity.this, response.body().getCopunsDetails()));
                    binding.recyclerviewCopunsList.setAdapter(new CopunsActivity().CopunsAdapter(CopunsActivity.this, response.body().getCopunsDetails()));


//                    binding.recyclerview_Reward_List.setAdapter(new RewardActivity.UploadAdapter(RewardActivity.this, response.body().getData()));
//                    binding.recyclerviewList.setAdapter(new UploadActivity.UploadAdapter(UploadActivity.this,response.body().getData()));


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<CopunsResponse> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

    }


    class CopunsAdapter extends RecyclerView.Adapter<CopunsActivity.CopunsAdapter.ViewHolder> {
        ArrayList<CopunsResponse.CopunsDetail> list;
        Context context;

        public CopunsAdapter(Context context, ArrayList<CopunsResponse.CopunsDetail> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public CopunsActivity.CopunsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CopunsListItemBinding copunsListItemBinding = CopunsListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new CopunsActivity().CopunsAdapter.ViewHolder(copunsListItemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CopunsActivity.CopunsAdapter.ViewHolder holder, int position) {
            CopunsResponse.CopunsDetail copunsDetail = list.get(position);

//            holder.binding.iv_profile_img1.setText(copunsDetail.getAttachment());
            holder.binding.ivProfileImg1.setText(copunsDetail.getAttachment());

//            holder.binding.tvStationCode.setText(copunsDetail.getStation_name()+" ("+copunsDetail.getStationId()+")");
//
//            holder.binding.tvLoc.setText(copunsDetail.getBrand());
//            holder.binding.tvAmnt.setText(copunsDetail.getAmount());
//            holder.binding.tvType.setText(copunsDetail.getSubcategory_name());
//            holder.binding.tvTime.setText(copunsDetail.getLastupdated());

//            holder.binding.tvStatus.setText(copunsDetail.getStatus());
//            holder.binding.tv_StationCode1.setText(copunsDetail.getStatus());

            holder.binding.tvStationCode1.setText(copunsDetail.getStatus());

//            Glide.with(CopunsActivity.this).load(catList.get(position).getName()).into(holder.project_img);
//            Glide.with(CopunsActivity.this).load(Constants.LOGO_IMG_URL + stationDataList.get(position).getBrandIcon()).into(holder.project_img);
//
//
//            Glide.with(CopunsActivity.this).load(position.body().getBase_path() + position.body().getCopunsDetails().getAttachment())
//                    .error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfileImg1);
//
//
//            Glide.with(CopunsActivity.this).load(position.body().getBase_path())
//                    .error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfileImg1);



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            //            UploadsListItemBinding binding;
            CopunsListItemBinding binding;


            public ViewHolder(@NonNull CopunsListItemBinding ubinding) {
                super(ubinding.getRoot());
                binding = ubinding;
            }
        }
    }


}