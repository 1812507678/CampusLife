package com.hainiu.campuslife.activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.BianqianInfo;
import com.hainu.campuslife.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.listener.SaveListener;

public class AddBianqianActivity extends Activity {

	private EditText ed_addbianqina_title;
	private EditText ed_addbianqina_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bianqian);

		ed_addbianqina_title = (EditText) findViewById(R.id.ed_addbianqina_title);
		ed_addbianqina_content = (EditText) findViewById(R.id.ed_addbianqina_content);
	}

	public void addBianqian(View view){
		String title = ed_addbianqina_title.getText().toString();
		String content = ed_addbianqina_content.getText().toString();

		BianqianInfo bianqianInfo = new BianqianInfo();
		bianqianInfo.setTitle(title);
		bianqianInfo.setContent(content);
		bianqianInfo.setBianqianId(System.currentTimeMillis()+"");
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		bianqianInfo.setUserId(id);

		Date date=new Date();//获取时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
		String formatData = sdf.format(date);
		bianqianInfo.setDate(formatData);

		bianqianInfo.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				Toast.makeText(AddBianqianActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
				setResult(141);
				finish();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast.makeText(AddBianqianActivity.this,"添加失败"+s,Toast.LENGTH_SHORT).show();
			}
		});
	}


}
