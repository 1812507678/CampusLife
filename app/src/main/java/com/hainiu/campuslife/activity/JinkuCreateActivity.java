package com.hainiu.campuslife.activity;

import com.hainu.campuslife.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class JinkuCreateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinku_create);
	}
	
	public void addjinkuiitem(View view) {
		startActivity(new Intent(this,JinkuAddActivity.class));
	}
}
