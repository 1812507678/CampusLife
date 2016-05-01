package com.hainiu.campuslife.activity;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.hainu.campuslife.R;

public class AlbumActivity extends Activity {

	private GridView gv_album_showalbum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		
		
		gv_album_showalbum = (GridView) findViewById(R.id.gv_album_showalbum);
		
		initView();
		
		
	}

	private void initView() {
		gv_album_showalbum.setAdapter(new AlbumListDataAdapter());
		
		gv_album_showalbum.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(AlbumActivity.this,AlbumInfoShowActivity.class));
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
			View inflate = View.inflate(AlbumActivity.this, R.layout.item_album, null);
			return inflate;
		}
		
	}
}
