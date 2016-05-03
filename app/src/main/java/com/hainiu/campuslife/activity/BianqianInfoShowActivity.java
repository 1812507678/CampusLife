package com.hainiu.campuslife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hainu.campuslife.R;

public class BianqianInfoShowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biqanqian_info_show);

        TextView tv_bianqinainfo_title = (TextView) findViewById(R.id.tv_bianqinainfo_title);
        TextView tv_bianqinainfo_content = (TextView) findViewById(R.id.tv_bianqinainfo_content);
        TextView tv_bianqinainfo_date = (TextView) findViewById(R.id.tv_bianqinainfo_date);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String date = intent.getStringExtra("date");

        tv_bianqinainfo_title.setText(title);
        tv_bianqinainfo_content.setText(content);
        tv_bianqinainfo_date.setText(date);
    }
}
