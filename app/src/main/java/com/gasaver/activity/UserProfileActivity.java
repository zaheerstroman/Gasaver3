package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gasaver.R;
import com.gasaver.Response.ProfileUserDataResponseGasaverT;
import com.gasaver.fragment.EditProfileFragment;
import com.gasaver.network.APIClient;
import com.gasaver.network.ApiInterface;
import com.gasaver.utils.CommonUtils;
import com.gasaver.utils.Constants;
import com.gasaver.utils.SharedPrefs;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView userProfile_G, user_Name_or_MobileNumber, user_Mobile_or_Name, tv_profile_name, tv_email, tv_phone, tv_current_plan, tv_role;
    ImageView iv_edit;
    LinearLayout ll_logout;


    private ProfileUserDataResponseGasaverT profileUserDataResponseGasaverT;

    private EditProfileFragment editProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile);
        setContentView(R.layout.activity_main_profile_demo);

        init();
        fetchProfileDetails();

        userProfile_G = findViewById(R.id.userProfile_G);

//        userProfile_G.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(UserProfileActivity.this, ProfileMainActivity.class);
//                Intent intent = new Intent(UserProfileActivity.this, EditProfileFragment.class);
//
//            }
//        });


    }

    private void init() {

        tv_role = findViewById(R.id.tv_role);
//        iv_profile_img = findViewById(R.id.iv_profile_img);
        tv_profile_name = findViewById(R.id.tv_profile_name);

//        tv_email = findViewById(R.id.tv_email);
//        tv_phone = findViewById(R.id.tv_phone);

        user_Name_or_MobileNumber = findViewById(R.id.user_Name_or_MobileNumber);
        user_Mobile_or_Name = findViewById(R.id.user_Mobile_or_Name);

        tv_current_plan = findViewById(R.id.tv_current_plan);
//        btn_upgrade = findViewById(R.id.btn_upgrade);

        iv_edit = findViewById(R.id.iv_edit);
        userProfile_G = findViewById(R.id.userProfile_G);


        ll_logout = findViewById(R.id.ll_logout);


        iv_edit.setOnClickListener(this);
        userProfile_G.setOnClickListener(this);
        user_Mobile_or_Name.setOnClickListener(this);


    }

    private void fetchProfileDetails() {

//        Fragment
//        CommonUtils.showLoading(getActivity(), "Please Wait", false);

        //Activity
        CommonUtils.showLoading(UserProfileActivity.this, "Please Wait", false);


        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("post_type", "profile_details")
//        SharedPrefs.getInstance(UserSignInActivity.this).saveString(Constants.USER_ID, response.body().getUserDetails().get(0).getUser_id());
//                .addFormDataPart("user_id", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_ID))
//                .addFormDataPart("mobile", SharedPrefs.getInstance(getActivity()).getString(Constants.USER_MOBILE))
//                .addFormDataPart("mobile", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.USER_MOBILE))
//                .addFormDataPart("api_key", SharedPrefs.getInstance(getActivity()).getString(Constants.API_KEY))
//                .addFormDataPart("api_key", SharedPrefs.getInstance(ProfileMainActivity.this).getString(Constants.API_KEY))

                .addFormDataPart("user_id", SharedPrefs.getInstance(UserProfileActivity.this).getString(Constants.USER_ID))
                .addFormDataPart("token", SharedPrefs.getInstance(UserProfileActivity.this).getString(Constants.TOKEN))
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
//                    tv_email.setText((CharSequence) profileUserDataResponseGasaverT.getData().getEmail());
                    user_Name_or_MobileNumber.setText((CharSequence) profileUserDataResponseGasaverT.getData().getEmail());
                    user_Mobile_or_Name.setText((CharSequence) profileUserDataResponseGasaverT.getData().getMobile());

//                    tv_profile_name.setText("Welcome " + profileDetailsResponse.getData().getFirst_name() + " " + profileDetailsResponse.getProfileDetails().getLast_name() + "!");
//                    tv_profile_name.setText("Welcome " + profileUserDataResponseGasaverT.getData().getName() + " " + profileUserDataResponseGasaverT.getData().getLastName() + "!");

//                    tv_phone.setText(profileDetailsResponseGasaverT.getData().getMobile());
//                    tv_phone.setText(new ProfileDetailsResponseGasaverT.Data().getMobile());
//                    tv_phone.setText(profileDetailsResponseGasaverT.Data().getMobile());

//                    tv_phone.setText(profileUserDataResponseGasaverT.getData().getMobile());
                    user_Mobile_or_Name.setText(profileUserDataResponseGasaverT.getData().getMobile());

//                    tv_phone.setText(profileDetailsResponseGasaverT.Data.getMobile());
//                    tv_phone.setText(profileUserDataResponseGasaverT.Data.getMobile());

//                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());
//                    tv_role.setText("Role: " + profileDetailsResponse.getData().getUser_role());
//                    tv_role.setText("Code: " + profileUserDataResponseGasaverT.getData().getCode());
//                    tv_current_plan.setText(profileDetailsResponse.getData().getUser_plan());
//                    tv_current_plan.setText(profileUserDataResponseGasaverT.getData().getUserCode());

//                    Glide.with(getActivity()).load(Constants.PROFILE_IMG_URL + profileDetailsResponse.getData().getAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);
//                    Glide.with(UserProfileActivity.this).load(Constants.PROFILE_IMG_URL + profileUserDataResponseGasaverT.getData().getProofAttachment()).error(R.drawable.profile_img).error(R.drawable.profile_img).into(iv_profile_img);

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
//            case R.id.iv_edit:
//                if (profileUserDataResponseGasaverT.getData() != null) {
//
//                    editProfileFragment = new EditProfileFragment();
//                    Bundle bundle = new Bundle();
//
////                    bundle.putString("ProfileDetails", new Gson().toJson(profileDetailsResponse.getProfileDetails()));
////                    bundle.putString("data", new Gson().toJson(profileDetailsResponseGasaverT.getData()));
//                    bundle.putString("data", new Gson().toJson(profileUserDataResponseGasaverT.getData()));
//
//                    editProfileFragment.setArguments(bundle);
//                    editProfileFragment.setDismissListener(new EditProfileFragment.DismissListener() {
//                        @Override
//                        public void onDismiss() {
//                            fetchProfileDetails();
//                        }
//                    });
////                    editProfileFragment.show(getParentFragmentManager(), "");
//                    editProfileFragment.show(getSupportFragmentManager(), "");
//
//                }
//                break;

            case R.id.userProfile_G:
                break;

            case R.id.ll_logout:
                SharedPrefs.getInstance(UserProfileActivity.this).clearSharedPrefs();
//                Intent intent1 = new Intent(getActivity(), SplashActivityGas.class);
//                Intent intent1 = new Intent(ProfileMainActivity.this, SplashActivity.class);
//                Intent intent1 = new Intent(ProfileMainActivity.this, SplashActivityGas.class);
                Intent intent1 = new Intent(UserProfileActivity.this, MainActivityGas.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                break;
        }

    }
}