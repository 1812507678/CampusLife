package com.hainiu.campuslife.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.hainu.campuslife.R;

public class AlbumInfoShowActivity extends Activity {

	private ListView lv_albumshow_shouinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album_info_show);
		
		lv_albumshow_shouinfo = (ListView) findViewById(R.id.lv_albumshow_shouinfo);
		
		initView();
		
		
	}

	private void initView() {
		lv_albumshow_shouinfo.setAdapter(new AlbumListDataAdapter());
		
		lv_albumshow_shouinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(AlbumActivity.this,AlbumInfoShowActivity.class));
			}
		});
		
	}
	
	class AlbumListDataAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 16;
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
			// TODO Auto-generated method stub
			View inflate = View.inflate(AlbumInfoShowActivity.this, R.layout.item_albuminfo, null);
			return inflate;
		}
		
	}
}
