package com.hainiu.campuslife.activity;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.JinkuCategory;
import com.hainu.campuslife.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;

public class JinkuCreateActivity extends Activity {

	private EditText ed_jinkucreate_name;
	private EditText ed_jinkucreate_totlacount;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinku_create);

		ed_jinkucreate_name = (EditText) findViewById(R.id.ed_jinkucreate_name);
		ed_jinkucreate_totlacount = (EditText) findViewById(R.id.ed_jinkucreate_totlacount);
	}

	public void crateJinkuConfirm(View view){
		String name = ed_jinkucreate_name.getText().toString();
		String totlacount = ed_jinkucreate_totlacount.getText().toString();

		JinkuCategory jinkuCategory = new JinkuCategory();
		jinkuCategory.setName(name);
		jinkuCategory.setTotalAmount(Integer.parseInt(totlacount));
		jinkuCategory.setBlanceAmount(0);
		jinkuCategory.setCategroyId(System.currentTimeMillis()+"");
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		jinkuCategory.setUserId(id);

		showDialog("正在创建...");
		jinkuCategory.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				hideDialog();
				Toast.makeText(JinkuCreateActivity.this,"创建成功",Toast.LENGTH_SHORT).show();
				setResult(111);
				finish();
			}

			@Override
			public void onFailure(int i, String s) {
				hideDialog();
				Toast.makeText(JinkuCreateActivity.this,"创建失败 "+s,Toast.LENGTH_SHORT).show();
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
