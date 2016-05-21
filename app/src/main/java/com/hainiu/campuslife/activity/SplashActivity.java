package com.hainiu.campuslife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hainu.campuslife.R;

import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    }
                });
            }
        }.start();


        SpotManager.getInstance(this).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
        SpotManager.getInstance(this).setAnimationType(SpotManager.ANIM_SIMPLE);
        SpotManager.getInstance(this).showSpotAds(this);


    }
}
