package com.gasaver.activity;

import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gasaver.R;
import com.gasaver.Response.BannersResponse;
import com.gasaver.adapter.ViewPagerAdapter;
import com.gasaver.databinding.ActivityRewardBinding;
import com.gasaver.fragment.AdvancedBannerSlidSearchFragment;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdvancedBannerSlidSearchActivity extends AppCompatActivity implements View.OnClickListener {

    AdvancedBannerSlidSearchActivity binding;


    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    ImageView iv_left_nav_viewpager, iv_right_nav_viewpager, iv_left_nav_proj, iv_right_nav_proj, iv_left_nav_prop, iv_right_nav_prop, iv_left_nav_agents, iv_right_nav_agents, iv_left_nav_dev, iv_right_nav_dev;
    int[] images = {R.drawable.sample2, R.drawable.sample3, R.drawable.sample4, R.drawable.sample5};

    //    private RecyclerView rv_latest_projects;
    private RecyclerView rv_latest_projects, rv_latest_properties, rv_agents, rv_dev;

    TextView tv_share_ur_req, tv_title;

    //    private LinearLayoutManager linearLayoutManager_proj;
    private LinearLayoutManager linearLayoutManager_proj, linearLayoutManager_prop, linearLayoutManager_agents, linearLayoutManager_dev;

    RelativeLayout rl_search;

    private int BannersResponse;

    //    private WishlistAdapter wishListAdapter;
    private AdvancedBannerSlidSearchActivity advancedBannerSlidSearchActivity;

    private AddBannerProjectAdapter addBannerProjectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_banner_slid_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("Promotions");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });

        fetchBanners();

        getLatestProjects();
//        AddBannerProjectAdapter();

        getAgents();

//        iv_left_nav_viewpager = view.findViewById(R.id.iv_left_nav_viewpager);
//        iv_right_nav_viewpager = view.findViewById(R.id.iv_right_nav_viewpager);
//        mViewPager = view.findViewById(R.id.viewpager);

        rv_latest_projects = findViewById(R.id.rv_latest_projects);
        rv_agents = findViewById(R.id.rv_agents);


        rv_latest_projects.setLayoutManager(linearLayoutManager_proj = new LinearLayoutManager(AdvancedBannerSlidSearchActivity.this, RecyclerView.HORIZONTAL, false));
//                rv_latest_projects.setLayoutManager(linearLayoutManager_proj = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

        rv_agents.setLayoutManager(linearLayoutManager_agents = new LinearLayoutManager(AdvancedBannerSlidSearchActivity.this, RecyclerView.HORIZONTAL, false));
//        rv_agents.setLayoutManager(linearLayoutManager_agents = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));


        iv_left_nav_viewpager = findViewById(R.id.iv_left_nav_viewpager);
        iv_right_nav_viewpager = findViewById(R.id.iv_right_nav_viewpager);

        iv_left_nav_proj = findViewById(R.id.iv_left_nav_proj);
        iv_right_nav_proj = findViewById(R.id.iv_right_nav_proj);

        iv_left_nav_agents = findViewById(R.id.iv_left_nav_agents);
        iv_right_nav_agents = findViewById(R.id.iv_right_nav_agents);

        mViewPager = findViewById(R.id.viewpager);

//        iv_left_nav_viewpager.setOnClickListener((View.OnClickListener) this);
//        iv_right_nav_viewpager.setOnClickListener((View.OnClickListener) this);

        iv_left_nav_viewpager.setOnClickListener(this);
        iv_right_nav_viewpager.setOnClickListener(this);

        iv_left_nav_proj.setOnClickListener(this);
        iv_right_nav_proj.setOnClickListener(this);

        iv_left_nav_agents.setOnClickListener(this);
        iv_right_nav_agents.setOnClickListener(this);

//        iv_left_nav_viewpager.setOnClickListener(this);
//        iv_right_nav_viewpager.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_nav_viewpager:
                mViewPager.arrowScroll(FOCUS_LEFT);
                break;
            case R.id.iv_right_nav_viewpager:
                mViewPager.arrowScroll(FOCUS_RIGHT);
                break;


            case R.id.iv_left_nav_proj:
                if (linearLayoutManager_proj.findFirstCompletelyVisibleItemPosition() > 0) {
                    linearLayoutManager_proj.scrollToPosition(linearLayoutManager_proj.findFirstCompletelyVisibleItemPosition() - 1);
                }
                break;
            case R.id.iv_right_nav_proj:
                if (linearLayoutManager_proj.findLastCompletelyVisibleItemPosition() < (rv_latest_projects.getAdapter().getItemCount() - 1)) {
                    linearLayoutManager_proj.scrollToPosition(linearLayoutManager_proj.findLastCompletelyVisibleItemPosition() + 1);
                }
                break;

            case R.id.iv_left_nav_agents:
                if (linearLayoutManager_agents.findFirstCompletelyVisibleItemPosition() > 0) {
                    linearLayoutManager_agents.scrollToPosition(linearLayoutManager_agents.findFirstCompletelyVisibleItemPosition() - 1);
                }
                break;
            case R.id.iv_right_nav_agents:
                if (linearLayoutManager_agents.findLastCompletelyVisibleItemPosition() < (rv_latest_properties.getAdapter().getItemCount() - 1)) {
                    linearLayoutManager_agents.scrollToPosition(linearLayoutManager_agents.findLastCompletelyVisibleItemPosition() + 1);
                }
                break;
        }
    }

    private void fetchBanners() {
//        CommonUtils.showLoading(getActivity(), "Please Wait", false);
        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        JsonObject jsonObject = new JsonObject();

//        jsonObject.addProperty("post_type", "banners");
        jsonObject.addProperty("user_id", "2168");
        jsonObject.addProperty("token", "51da1d874ab9626e5f851d02fa31472259d759ae508c988f38184582c0433fb1");

        Call<BannersResponse> call = apiInterface.fetchBanners(jsonObject);
//        Call<BannersResponse.AddsDetail> call = apiInterface.fetchBanners(jsonObject);

        call.enqueue(new Callback<BannersResponse>() {
//        call.enqueue(new Callback<BannersResponse.AddsDetail>() {

            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {
//            public void onResponse(Call<BannersResponse.AddsDetail> call, Response<BannersResponse.AddsDetail> response) {

                BannersResponse bannersResponse = response.body();
//                BannersResponse.AddsDetail bannersResponse = response.body();

//                if (bannersResponse != null && !bannersResponse.getBanners().isEmpty()) {
                if (bannersResponse != null && !bannersResponse.getAddsDetails().isEmpty()) {


                    // Initializing the ViewPagerAdapter
//                    mViewPagerAdapter = new ViewPagerAdapter(getActivity(), bannersResponse.getBanners());
                    mViewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), bannersResponse.getAddsDetails());
//                    mViewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), bannersResponse.getBasePath());

                    // Adding the Adapter to the ViewPager
                    mViewPager.setAdapter(mViewPagerAdapter);
                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
//            public void onFailure(Call<BannersResponse.AddsDetail> call, Throwable t) {

                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }

    //    private void getAddBannerProject() {
    private void getLatestProjects() {

//        CommonUtils.showLoading(getActivity(), "Please Wait", false);
        CommonUtils.showLoading(getApplicationContext(), "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)

//                .addFormDataPart("type", "latest_projects")
//                .addFormDataPart("location", "Hyderabad")
//                .addFormDataPart("property_id", "7")

//                .addFormDataPart("user_id", "2168")
//                .addFormDataPart("token", "51da1d874ab9626e5f851d02fa31472259d759ae508c988f38184582c0433fb1")
//
//                .addFormDataPart("user_id", 119)
                .addFormDataPart("user_id", "119").addFormDataPart("token", "24631cdd0323cea063e6cb4e5b2b0f6606540a5ae48428ed41e412131efb3c5a")
//                .addFormDataPart("company_id", 3)
                .addFormDataPart("company_id", "3").build();

//        Call<LatestPropertiesResponse> call = apiInterface.fetchLatestProperties(requestBody);
//        fetchBanners

        Call<BannersResponse> call = apiInterface.fetchBanners1(requestBody);
//        Call<BannersResponse> call = apiInterface.fetchBanners(jsonObject);


        //JsonObject
        //Call<BannersResponse> call = apiInterface.fetchBanners(JsonObject);


        call.enqueue(new Callback<BannersResponse>() {
            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {
                BannersResponse bannersResponse = response.body();

//                if (latestPropertiesResponse != null && !latestPropertiesResponse.getLatestProperties().isEmpty()) {
                if (bannersResponse != null && !bannersResponse.getCompanyDetails().isEmpty()) {
//                if (bannersResponse != null && !bannersResponse.getCompanyBasePath().isEmpty()) {


//                    rv_latest_projects.setAdapter(new ProjectAdapter(latestPropertiesResponse.getLatestProperties()));

//                    rv_latest_projects.setAdapter(new AddBannerProjectAdapter(bannersResponse.getCompanyBasePath()));

//                    rv_latest_projects.setAdapter(new AddBannerProjectAdapter(bannersResponse.getCompanyDetails()));
//                    binding.rv_latest_projects.setAdapter(new AdvancedBannerSlidSearchActivity.AddBannerProjectAdapter(AdvancedBannerSlidSearchActivity.this, response.body().getCompanyDetails()));
//                    binding.rv_latest_projects.setAdapter(advancedBannerSlidSearchActivity = new AdvancedBannerSlidSearchActivity.AddBannerProjectAdapter(AdvancedBannerSlidSearchActivity.this, bannersResponse));

//                    binding.rv_latest_projects.setAdapter(advancedBannerSlidSearchActivity = new AdvancedBannerSlidSearchActivity().AddBannerProjectAdapter(AdvancedBannerSlidSearchActivity.this, bannersResponse));
//                    binding.rv_latest_projects.setAdapter(advancedBannerSlidSearchActivity = AddBannerProjectAdapter(AdvancedBannerSlidSearchActivity.this, bannersResponse));


//                    rv_latest_projects.setAdapter(new AddBannerProjectAdapter(String.valueOf(bannersResponse.getCompanyDetails())));
//                    rv_latest_projects.setAdapter(new AddBannerProjectAdapter(bannersResponse.getCompanyDetails(),true));


//                    rv_latest_projects.setAdapter(new AddBannerProjectAdapter(bannersResponse.getCompanyBasePath(),true));

                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }

    private void getAgents() {
//        CommonUtils.showLoading(getActivity(), "Please Wait", false);
        CommonUtils.showLoading(AdvancedBannerSlidSearchActivity.this, "Please Wait", false);

        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)

//                .addFormDataPart("type", "get_agents")

                .addFormDataPart("user_id", "119").addFormDataPart("token", "24631cdd0323cea063e6cb4e5b2b0f6606540a5ae48428ed41e412131efb3c5a").addFormDataPart("company_id", "3").build();

//        Call<AgentsResponse> call = apiInterface.fetchAgents(requestBody);
        Call<BannersResponse> call = apiInterface.fetchBanners1(requestBody);

        call.enqueue(new Callback<BannersResponse>() {
            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {
                BannersResponse agentsResponse = response.body();
                if (agentsResponse != null && !agentsResponse.getCompanyDetails().isEmpty()) {

//                    rv_agents.setAdapter(new AddBannerProjectAdapter(agentsResponse.getCompanyDetails(),true));
//                    rv_agents.setAdapter(new AddBannerProjectAdapter(agentsResponse.getCompanyDetails(), true));

                }
                CommonUtils.hideLoading();
            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
                t.printStackTrace();
                CommonUtils.hideLoading();
            }
        });

    }


    //    public class ProjectAdapter extends RecyclerView.Adapter<ViewHolder> {
    public class AddBannerProjectAdapter extends RecyclerView.Adapter<ViewHolder> {


        //        ArrayList<LatestPropertiesResponse.LatestProperties> latestProperties;
//        ArrayList<BannersResponse.CompanyDetail> bannerCompanyDetails;

//        ArrayList<String> bannerCompanyDetails = new ArrayList<String>();

//        ArrayList<String> bannerCompanyDetails;
        ArrayList<BannersResponse> bannerCompanyDetails;
//        ArrayList<BannersResponse.CompanyDetail> bannerCompanyDetails;


//        ArrayList<BannersResponse> bannerCompanyDetails = new ArrayList<>(BannersResponse);


        //        ArrayList<AgentsResponse.AgentModel> agentModels;
//        ArrayList<BannersResponse.AddsDetail> agentModels;
//        ArrayList<BannersResponse.CompanyDetail> agentModels;

//        ArrayList<String> agentModels =new ArrayList<String>();

//        ArrayList<String> agentModels;
        ArrayList<BannersResponse> agentModels;
//        ArrayList<BannersResponse.CompanyDetail> agentModels;

//        ArrayList<BannersResponse.CompanyDetail> agentModels = new ArrayList<>(BannersResponse);


        boolean isAgent = false;

        //        public ProjectAdapter(ArrayList<LatestPropertiesResponse.LatestProperties> latestProperties) {
//        public AddBannerProjectAdapter(ArrayList<BannersResponse.CompanyDetail> bannerCompanyDetails) {
//        public AddBannerProjectAdapter(ArrayList<BannersResponse> bannerCompanyDetails) {
//
////            this.latestProperties = latestProperties;
//            this.bannerCompanyDetails = bannerCompanyDetails;
//
//        }

        //        public ProjectAdapter(ArrayList<AgentsResponse.AgentModel> agentModels, boolean isAgent) {
//        public AddBannerProjectAdapter(ArrayList<BannersResponse.CompanyDetail> agentModels, boolean isAgent) {
//        public AddBannerProjectAdapter(ArrayList<BannersResponse> agentModels, boolean isAgent) {
//
//            this.agentModels = agentModels;
//            this.isAgent = isAgent;
//        }

//        public AddBannerProjectAdapter(String companyBasePath) {
//            this.agentModels = agentModels;
//            this.isAgent = isAgent;
////            this.companyBasePath = companyBasePath;
//        }

        public AddBannerProjectAdapter(ArrayList<BannersResponse> companyDetails, boolean isAgent) {
            this.agentModels = companyDetails;
            this.isAgent = isAgent;
        }

        //        public AddBannerProjectAdapter(ArrayList<BannersResponse.CompanyDetail> companyDetails, boolean isAgent) {
//        public AddBannerProjectAdapter(ArrayList<BannersResponse> companyDetails, boolean isAgent) {
//            this.agentModels = companyDetails;
//            this.isAgent = isAgent;
//        }

//        public AddBannerProjectAdapter(ArrayList<String> companyDetails, boolean isAgent) {
//            this.agentModels = companyDetails;
//            this.isAgent = isAgent;
//        }


        //        public AddBannerProjectAdapter(ArrayList<BannersResponse.CompanyDetail> companyDetails) {
//        public AddBannerProjectAdapter(ArrayList<BannersResponse> bannerCompanyDetails) {
////        public AddBannerProjectAdapter(ArrayList<BannersResponse.CompanyDetail> bannerCompanyDetails) {
//
//            this.bannerCompanyDetails = bannerCompanyDetails;
//
//        }

        //        public AddBannerProjectAdapter(ArrayList<com.gasaver.Response.BannersResponse.CompanyDetail> companyDetails) {
        public AddBannerProjectAdapter(ArrayList<com.gasaver.Response.BannersResponse> bannerCompanyDetails) {
            this.bannerCompanyDetails = bannerCompanyDetails;
//            this.bannerCompanyDetails = companyDetails;
        }

//        public AddBannerProjectAdapter(ArrayList<String> bannerCompanyDetails) {
//            this.bannerCompanyDetails = bannerCompanyDetails;
////            this.bannerCompanyDetails = companyDetails;
//        }



        //        public UploadAdapter(Context context, ArrayList<GetRewardDataResponse.GetRewardData> list) {
//            this.context = context;
//            this.list = list;
//        }

//        public AddBannerProjectAdapter(AdvancedBannerSlidSearchActivity advancedBannerSlidSearchActivity, ArrayList<com.gasaver.Response.BannersResponse.CompanyDetail> companyDetails) {
//            this.advancedBannerSlidSearchActivity = advancedBannerSlidSearchActivity;
//            this.companyDetails = companyDetails;
//        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_projects, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (isAgent) {

//                Glide.with(getActivity()).load(agentModels.get(position).getImage()).into(holder.project_img);

//                Glide.with(getActivity()).load(agentModels.get(position).getCompanyBasePath()).into(holder.project_img);

                Glide.with(getApplicationContext()).load(bannerCompanyDetails.get(position).getCompanyBasePath() + bannerCompanyDetails.get(position).getCompanyDetails().get(position).getLogo()).into(holder.project_img);

//                bannerCompanyDetails
//                company_base_path


//                Glide.with(getActivity()).load(response.body().getBase_path() + response.body().getData().getProfilePhoto())
//                        .error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfileImg);


//                Glide.with(getActivity()).load(response.body().getBarCode())
//                        .error(R.drawable.profile_img).error(R.drawable.profile_img).into(binding.ivProfileImg1);


                holder.project_loc.setVisibility(View.GONE);
//                holder.project_builder.setText(agentModels.get(position).getLocation());
//                holder.project_builder.setText(agentModels.get(position).getLogo());

//                holder.project_builder.setText(agentModels.get(position).getDescription());

//                holder.project_title.setText(agentModels.get(position).getName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
//                        i.setData(Uri.parse(agentModels.get(position).getUrl()));
                        i.setData(Uri.parse(agentModels.get(position).getCompanyBasePath()));
//                        i.setData(Uri.parse(agentModels.get(position).getLogo()));

                        startActivity(i);
                    }
                });
            } else {

//                Glide.with(getActivity()).load(Constants.PROPERTY_IMAGE_URL + latestProperties.get(position).getProjectImg()).into(holder.project_img);
//                Glide.with(getActivity()).load(Constants.PROPERTY_IMAGE_URL + bannerCompanyDetails.get(position).getCompanyBasePath()).into(holder.project_img);
//                Glide.with(AdvancedBannerSlidSearchActivity).load(Constants.PROPERTY_IMAGE_URL + bannerCompanyDetails.get(position).getCompanyBasePath()).into(holder.project_img);
//                Glide.with(getApplicationContext()).load(Constants.PROPERTY_IMAGE_URL + bannerCompanyDetails.get(position).getCompanyBasePath()).into(holder.project_img);

//                Glide.with(AdvancedBannerSlidSearchActivity.this).load(bannerCompanyDetails.get(position).getCompanyBasePath() +
//                                bannerCompanyDetails.get(position).getCompanyDetails().get(position).getLogo())
//                        .into(holder.project_img);

//                holder.project_loc.setText(latestProperties.get(position).getCityVillage());
                holder.project_loc.setText(bannerCompanyDetails.get(position).getCompanyBasePath());

//                holder.project_builder.setText(latestProperties.get(position).getBuildername());
                holder.project_builder.setText(bannerCompanyDetails.get(position).getCompanyBasePath());

//                holder.project_title.setText(latestProperties.get(position).getTitle());
                holder.project_title.setText(bannerCompanyDetails.get(position).getBasePath());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(getActivity(), PropertyDetailsActivity.class);
//                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

//                        intent.putExtra("propertyId", (latestProperties.get(position).getId()));
//                        intent.putExtra("propertyId", (bannerCompanyDetails.get(position).getBasePath()));
                        intent.putExtra("Id", (bannerCompanyDetails.get(position).getBasePath()));

                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            if (isAgent) return agentModels.size();
            else
//                return latestProperties.size();
                return bannerCompanyDetails.size();

        }
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView project_builder, project_loc, project_title;
        ImageView project_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            project_img = itemView.findViewById(R.id.project_img);
            project_builder = itemView.findViewById(R.id.project_builder);
            project_loc = itemView.findViewById(R.id.project_loc);
            project_title = itemView.findViewById(R.id.project_title);
        }
    }


}