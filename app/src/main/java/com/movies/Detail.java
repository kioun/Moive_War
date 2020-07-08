package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import java.util.Date;

public class Detail extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //애드몹 광고
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-9265826547932231~4158215863");

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String rating = intent.getStringExtra("rating");
        String director = intent.getStringExtra("director");
        String actor = intent.getStringExtra("actor");
        String subtitle = intent.getStringExtra("subtitle");
        String pubdate = intent.getStringExtra("pubdate");


        TextView tv_dt_title = findViewById(R.id.tv_dt_title);
        tv_dt_title.setText(Html.fromHtml(title));
        TextView tv_dt_rating = findViewById(R.id.tv_dt_rating);
        tv_dt_rating.setText(Html.fromHtml(rating+"/10"));
        TextView tv_dt_director = findViewById(R.id.tv_dt_director);
        tv_dt_director.setText(Html.fromHtml(director));
        TextView tv_dt_actor = findViewById(R.id.tv_dt_actor);
        tv_dt_actor.setText(Html.fromHtml(actor));
        TextView tv_dt_subtitle = findViewById(R.id.tv_dt_subtitle);
        tv_dt_subtitle.setText(Html.fromHtml(subtitle));
        TextView tv_dt_pubdate = findViewById(R.id.tv_dt_pubdate);
        tv_dt_pubdate.setText(pubdate);
        ImageView iv_dt_image = findViewById(R.id.iv_dt_image);
        Glide.with(this)
                .load(image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_dt_image);
    }
}
