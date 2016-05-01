package com.hainiu.campuslife.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.hainu.campuslife.R;

public class MainActivity extends Activity {
	private ImageButton ib_main_xiaojinku;
	private ImageButton ib_main_bianqian;
	private ImageButton ib_main_jiluxiangce;
	private ImageButton ib_main_shenghuorili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        ib_main_xiaojinku=(ImageButton)findViewById(R.id.ib_main_xiaojinku);
        ib_main_bianqian = (ImageButton) findViewById(R.id.ib_main_bianqian);
        ib_main_jiluxiangce = (ImageButton) findViewById(R.id.ib_main_jiluxiangce);
        ib_main_shenghuorili = (ImageButton) findViewById(R.id.ib_main_shenghuorili);
        

        initViewOnClick();

    }

	private void initViewOnClick() {
		ib_main_xiaojinku.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,JinKuActivity.class));
			}
		});
		
		ib_main_bianqian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,BianQianActivity.class));
			}
		});
		
		ib_main_jiluxiangce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,AlbumActivity.class));
			}
		});
		
		ib_main_shenghuorili.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,CalendarActicivity.class));
			}
		});
		
		
	}
}
