package com.hainiu.campuslife.activity;


import com.hainu.campuslife.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class BianQianActivity extends Activity {

	private ListView lv_bainqian_showbianqian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bianqian);
		
		lv_bainqian_showbianqian = (ListView) findViewById(R.id.lv_bainqian_showbianqian);
		
		initViewData();
	}
	
	private void initViewData() {
		lv_bainqian_showbianqian.setAdapter(new ShowBianqianAdapter());
		
	}

	public void additem(View view) {
		startActivity(new Intent(this,AddBianqianActivity.class));
	}
	
	class ShowBianqianAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 20;
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
			View inflate = View.inflate(BianQianActivity.this, R.layout.bianqian_item, null);
			return inflate;
		}
		
	}
}
