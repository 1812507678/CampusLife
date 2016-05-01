package com.hainiu.campuslife.activity;


import com.hainu.campuslife.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class JinKuActivity extends Activity {
	protected static final String TAG = "JinKuActivity";
	private GridView gv_jinku_info;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinku);
      
        gv_jinku_info = (GridView)findViewById(R.id.gv_jinku_info);
        
        initView();
    }
	
	private void initView() {
		gv_jinku_info.setAdapter(new MyAdapter());
		
		gv_jinku_info.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onItemClick: "+position);
				startActivity(new Intent(JinKuActivity.this,JinkuInforShowActivity.class));
			}
		});
		
	}
	
	public void createjinku(View view) {
		startActivity(new Intent(this,JinkuCreateActivity.class));
	}
	
	
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 12;
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
			View inflate = View.inflate(JinKuActivity.this, R.layout.item_envelope, null);
			return inflate;
		}
		
	}

}
