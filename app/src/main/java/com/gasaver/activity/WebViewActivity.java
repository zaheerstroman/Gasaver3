package com.gasaver.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.gasaver.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        init();

        String image=getIntent().getStringExtra("Attachment");
//        String image=getIntent().getStringExtra("Terms Conditions");
        webView.loadUrl(image);
    }
    private void init() {


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

//        getSupportActionBar().setTitle("ViewAttachment");
        getSupportActionBar().setTitle("Terms & Conditions");
//        getSupportActionBar().setTitle("Privacy Policy");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do something you want
                finish();
            }
        });
        webView = findViewById(R.id.iv_attachment);
        webView.getSettings().setBuiltInZoomControls(true);
    }

}