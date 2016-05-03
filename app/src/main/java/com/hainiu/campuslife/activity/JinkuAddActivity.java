package com.hainiu.campuslife.activity;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.JinkuCategory;
import com.hainiu.campuslife.bean.KinkuPayDetial;
import com.hainu.campuslife.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class JinkuAddActivity extends Activity {

	private EditText ed_jinkuadd_name;
	private DatePicker dp_jinkuadd_data;
	private EditText ed_jinkuadd_moey;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinku_add);

		ed_jinkuadd_name = (EditText) findViewById(R.id.ed_jinkuadd_name);
		dp_jinkuadd_data = (DatePicker) findViewById(R.id.dp_jinkuadd_data);
		ed_jinkuadd_moey = (EditText) findViewById(R.id.ed_jinkuadd_moey);
	}

	public void confirmAdd(View view){
		String name = ed_jinkuadd_name.getText().toString();
		final String money = ed_jinkuadd_moey.getText().toString();

		int dayOfMonth = dp_jinkuadd_data.getDayOfMonth();
		int month = dp_jinkuadd_data.getMonth();
		int year = dp_jinkuadd_data.getYear();

		String date = year+"-"+month+"-"+dayOfMonth;

		KinkuPayDetial kinkuPayDetial = new KinkuPayDetial();
		kinkuPayDetial.setPayId(System.currentTimeMillis()+"");
		kinkuPayDetial.setPaySubject(name);
		kinkuPayDetial.setPayCount(Integer.parseInt(money));

		final Intent intent = getIntent();
		String belongto = intent.getStringExtra("name");
		kinkuPayDetial.setBlongToJinku(belongto);
		kinkuPayDetial.setPayData(date);

		String id = ApplicationInfo.sharedPreferences.getString("id","");
		kinkuPayDetial.setUserId(id);

		showDialog("正在添加...");
		kinkuPayDetial.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				String blanceAmount = intent.getStringExtra("blanceAmount");
				String objectId = intent.getStringExtra("objectId");

				JinkuCategory jinkuCategory = new JinkuCategory();
				final int blance = Integer.parseInt(blanceAmount) - Integer.parseInt(money);
				jinkuCategory.setBlanceAmount(blance);
				jinkuCategory.setObjectId(objectId);
				jinkuCategory.update(JinkuAddActivity.this, new UpdateListener() {
					@Override
					public void onSuccess() {
						hideDialog();
						Toast.makeText(JinkuAddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
						intent.putExtra("blanceAmount",blance+"");
						setResult(121,intent);
						finish();
					}

					@Override
					public void onFailure(int i, String s) {

					}
				});

			}

			@Override
			public void onFailure(int i, String s) {
				hideDialog();
				Toast.makeText(JinkuAddActivity.this,"添加失败"+s,Toast.LENGTH_SHORT).show();
			}
		});


	}

	void showDialog(String message) {
		try {
			if (dialog == null) {
				dialog = new ProgressDialog(this);
				dialog.setCancelable(true);
			}
			dialog.setMessage(message);
			dialog.show();
		} catch (Exception e) {
			// 在其他线程调用dialog会报错
		}
	}

	void hideDialog() {
		if (dialog != null && dialog.isShowing())
			try {
				dialog.dismiss();
			} catch (Exception e) {
			}
	}
}
