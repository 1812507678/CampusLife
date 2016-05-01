package com.hainiu.campuslife.activity;

import com.hainu.campuslife.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class CalendarRemindActivity extends Activity {

	private ListView lv_calendarremind_remind;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_remind);
		
		lv_calendarremind_remind = (ListView) findViewById(R.id.lv_calendarremind_remind);
		
		initviewData();
	}
	
	private void initviewData() {
		// TODO Auto-generated method stub
		lv_calendarremind_remind.setAdapter(new JinkuInforAdapter());
	}
	
	public void addRemind(View view) {
		startActivity(new Intent(this,RemindAddActivity.class));
	}
	
	class JinkuInforAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 8;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View inflate = View.inflate(CalendarRemindActivity.this, R.layout.item_jinkushow, null);
			return inflate;
		}
		
	}
}
