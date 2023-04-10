package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
//import com.e.gasserviceapp.ApiInterface;
import com.gasaver.R;
import com.gasaver.Response.ProfileUserDataResponseGasaverT;
import com.gasaver.fragment.EditProfileFragment;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;
import com.google.android.material.imageview.ShapeableImageView;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class ProfileFragment extends Fragment implements View.OnClickListener {
//Fragment to Activity
public class ProfileMainActivity extends AppCompatActivity implements View.OnClickListener {
    //public class ProfileMainActivity extends AppCompatActivity implements View.OnClickListener {
//
    ShapeableImageView iv_profile_img;
    ImageView iv_edit;
    LinearLayout ll_logout, layout_myResponses, layout_savedSearches, layout_shortListed, layout_contaced, layout_myRequirements, layout_myProperties;
    TextView tv_profile_name, tv_email, tv_phone, tv_current_plan, tv_role;

    //    private ProfileDetailsResponse profileDetailsResponse;
//    private ProfileDetailsResponseGasaverT profileDetailsResponse;
    private ProfileUserDataResponseGasaverT profileUserDataResponseGasaverT;

    private EditProfileFragment editProfileFragment;
    Button btn_upgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
//        setContentView(R.layout.activity_main_profile_demo);
        //ProfileMainActivity

//        init(view);
        init();
        fetchProfileDetails();
    }

    private void init() {
        tv_role = findViewById(R.id.tv_role);
        iv_profile_img = findViewById(R.id.iv_profile_img);
        tv_profile_name = findViewById(R.id.tv_profile_name);

        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);

        tv_current_plan = findViewById(R.id.tv_current_plan);
//        btn_upgrade = findViewById(R.id.btn_upgrade);

        iv_edit = findViewById(R.id.iv_edit);

        ll_logout = findViewById(R.id.ll_logout);

//        layout_myResponses = findViewById(R.id.layout_myResponses);
//        layout_savedSearches = findViewById(R.id.layout_savedSearches);
//        layout_shortListed = findViewById(R.id.layout_shortListed);
//        layout_contaced = findViewById(R.id.layout_contaced);
//        layout_myRequirements = findViewById(R.id.layout_myRequirements);
//        layout_myProperties = findViewById(R.id.layout_myProperties);

//        ll_logout.setOnClickListener(this);
//
//        layout_myResponses.setOnClickListener(this);
//        layout_savedSearches.setOnClickListener(this);
//        layout_shortListed.setOnClickListener(this);
//        layout_contaced.setOnClickListener(this);
//        layout_myRequirements.setOnClickListener(this);
//        layout_myProperties.setOnClickListener(this);

        iv_edit.setOnClickListener(this);
    }

    private void fetchProfileDetails() {

//        Fragment
//        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        //Activity
        CommonUtils.showLoading(ProfileMainActivity.this, "Please Wait", false);


        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("post_type", "profile_details")
//        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_ID, response.body().getUserDetails().get(0).getUser_id());
//                .addFormDataPart("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID))
//                .addFormDataPart("mobile", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_MOBILE))
//                .addFormDataPart("mobile", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.USER_MOBILE))
//                .addFormDataPart("api_key", SharedPrefs.getInstance(getActivity()).getString(Constants.API_KEY))
//                .addFormDataPart("api_key", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.API_KEY))

                .addFormDataPart("user_id", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.TOKEN))
                .build();

//        Call<ProfileDetailsResponse> call = apiInterface.fetchProfileDetails(requestBody);
//        Call<ProfileDetailsResponseGasaverT> call = apiInterface.fetchProfileDetails(requestBody);
        Call<ProfileUserDataResponseGasaverT> call = apiInterface.fetchProfileDetails(requestBody);

        call.enqueue(new Callback<ProfileUserDataResponseGasaverT>() {
            @Override
            public void onResponse(Call<ProfileUserDataResponseGasaverT> call, Response<ProfileUserDataResponseGasaverT> response) {
                CommonUtils.hideLoading();

                profileUserDataResponseGasaverT = (ProfileUserDataResponseGasaverT) response.body();

                if (profileUserDataResponseGasaverT != null && profileUserDataResponseGasaverT.getData() != null) {

//                    tv_email.setText(profileDetailsResponseGasaverT.getData().getEmail());
                    tv_email.setText((CharSequence) profileUserDataResponseGasaverT.getData().getEmail());
//                    tv_profile_name.setText("Welcome " + profileDetailsResponse.getData().getFirst_name() + " " + profileDetailsResponse.getProfileDetails().getLast_name() + "!");
                    tv_profile_name.setText("Welcome " + profileUserDataResponseGasaverT.getData().getName() + " " + profileUserDataResponseGasaverT.getData().getLastName() + "!");

//                    tv_phone.setText(profileDetailsResponseGasaverT.getData().getMobile());
//                    tv_phone.setText(new ProfileDetailsResponseGasaverT.Data().getMobile());
//                    tv_phone.setText(profileDetailsResponseGasaverT.Data().getMobile());

                    tv_phone.setText(profileUserDataResponseGasaverT.getData().getMobile());
//                    tv_phone.setText(profileDetailsResponseGasaverT.Data.getMobile());
//                    tv_phone.setText(profileUserDataResponseGasaverT.Data.getMobile());

                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());
//                    tv_role.setText("Role: " + profileDetailsResponse.getData().getUser_role());
                    tv_role.setText("Code: " + profileUserDataResponseGasaverT.getData().getCode());
//                    tv_current_plan.setText(profileDetailsResponse.getData().getUser_plan());
                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());

//                    Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profileDetailsResponse.getData().getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);
                    Glide.with(ProfileMainActivity.this).load(Constants.PROFILE_IMG_URL + profileUserDataResponseGasaverT.getData().getProofAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);

                }


            }

            @Override
            public void onFailure(Call<ProfileUserDataResponseGasaverT> call, Throwable t) {
                CommonUtils.hideLoading();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.layout_myResponses:
//                Intent intent = new Intent(ProfileMainActivity.this, ProfileDetailsActivity.class);
//                intent.putExtra("SELECTED_POS", 0);
//                startActivity(intent);
//                break;
//            case R.id.layout_savedSearches:
//                intent = new Intent(ProfileMainActivity.this, ProfileDetailsActivity.class);
//                intent.putExtra("SELECTED_POS", 5);
//                startActivity(intent);
//                break;
//            case R.id.layout_shortListed:
//                intent = new Intent(ProfileMainActivity.this, ProfileDetailsActivity.class);
//                intent.putExtra("SELECTED_POS", 4);
//                startActivity(intent);
//                break;
//            case R.id.layout_contaced:
//                intent = new Intent(ProfileMainActivity.this, ProfileDetailsActivity.class);
//                intent.putExtra("SELECTED_POS", 3);
//                startActivity(intent);
//                break;
//            case R.id.layout_myRequirements:
//                intent = new Intent(ProfileMainActivity.this, ProfileDetailsActivity.class);
//                intent.putExtra("SELECTED_POS", 2);
//                startActivity(intent);
//                break;
//            case R.id.layout_myProperties:
//                intent = new Intent(ProfileMainActivity.this, ProfileDetailsActivity.class);
//                intent.putExtra("SELECTED_POS", 1);
//                startActivity(intent);
//                break;
            case R.id.ll_logout:
                SharedPrefs.getInstance(ProfileMainActivity.this).clearSharedPrefs();
//                Intent intent1 = new Intent(getActivity(), SplashActivityGas.class);
//                Intent intent1 = new Intent(ProfileMainActivity.this, SplashActivity.class);
//                Intent intent1 = new Intent(ProfileMainActivity.this, SplashActivityGas.class);
                Intent intent1 = new Intent(ProfileMainActivity.this, MainActivityGas.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;

        }

    }


//    @Override
//    public void onClick(View v) {
//
//    }
}