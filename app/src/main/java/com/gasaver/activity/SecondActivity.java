package com.gasaver.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gasaver.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {

//        Button Signout;
    Context context = SecondActivity.this;
    GoogleSignInClient mGoogleSignInClient;
    Button googlesignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button signout = findViewById(R.id.sign_in_button);



//        Button logout = findViewById(R.id.googlesignout);
        Button googlesignout = findViewById(R.id.googlesignout);

        googlesignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                finish();
            }
        });

//        Signout.setSize(SignInButton.SIZE_STANDARD);
//        signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SignOut(view);
//            }
//        });
//         Signout = findViewById(R.id.signout);



//        onclicklogout();
    }

    private void SignOut(View view) {

        switch (view.getId()) {
            // ...
//            case R.id.signout:
//                SignOut(view);
//                break;

            // ...
        }
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    //    private void onclicklogout() {
//        Signout.setOnClickListener(view -> startActivity(new Intent(context,MainActivity.class)));
//    }
//
//private void signOut() {
//
//    mGoogleSignInClient.signOut()
//            .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    // ...
//                }
//            });
//}
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}