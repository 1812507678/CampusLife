package com.hainiu.campuslife.activity;

import com.hainu.campuslife.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class JinkuInforShowActivity extends Activity {

	private ListView lv_jinukuinfor_showitem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinku_infor_show);
		
		lv_jinukuinfor_showitem = (ListView) findViewById(R.id.lv_jinukuinfor_showitem);
		initviewData();
	}

	private void initviewData() {
		// TODO Auto-generated method stub
		lv_jinukuinfor_showitem.setAdapter(new JinkuInforAdapter());
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
			View inflate = View.inflate(JinkuInforShowActivity.this, R.layout.item_jinkushow, null);
			return inflate;
		}
		
	}
}
