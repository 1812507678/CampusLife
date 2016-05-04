package com.hainiu.campuslife.activity;

import com.hainu.campuslife.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalendarActicivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
	}
	
	public void showCalendar(View view) {
		startActivity(new Intent(this,CalendarManageActivity.class));
	}
	
	public void showTask(View view) {
		startActivity(new Intent(this,TaskManageActivity.class));
	}

	public void showRemind(View view) {
		startActivity(new Intent(this,CalendarRemindActivity.class));
	}

}
