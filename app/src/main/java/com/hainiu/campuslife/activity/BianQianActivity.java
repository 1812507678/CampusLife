package com.hainiu.campuslife.activity;


import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.BianqianInfo;
import com.hainu.campuslife.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class BianQianActivity extends Activity {

	private ListView lv_bainqian_showbianqian;
	private List<BianqianInfo> bianqianInfoList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bianqian);
		
		lv_bainqian_showbianqian = (ListView) findViewById(R.id.lv_bainqian_showbianqian);
		
		initViewData();
	}
	
	private void initViewData() {
		bianqianInfoList = new ArrayList<>();

		BmobQuery<BianqianInfo> bmobQuery = new BmobQuery<>();
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		bmobQuery.addWhereEqualTo("userId",id);
		bmobQuery.findObjects(this, new FindListener<BianqianInfo>() {
			@Override
			public void onSuccess(List<BianqianInfo> list) {
				bianqianInfoList = list;
				lv_bainqian_showbianqian.setAdapter(new ShowBianqianAdapter());
			}

			@Override
			public void onError(int i, String s) {

			}
		});

		lv_bainqian_showbianqian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				BianqianInfo bianqianInfo = bianqianInfoList.get(position);
				Intent intent = new Intent(BianQianActivity.this,BianqianInfoShowActivity.class);
				intent.putExtra("title",bianqianInfo.getTitle());
				intent.putExtra("content",bianqianInfo.getContent());
				intent.putExtra("date",bianqianInfo.getDate());
				startActivity(intent);
			}
		});

		
	}

	public void additem(View view) {
		Intent intent = new Intent(this, AddBianqianActivity.class);
		startActivityForResult(intent,140);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 140 && resultCode == 141) {
			initViewData();
		}
	}
	
	class ShowBianqianAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return bianqianInfoList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			BianqianInfo bianqianInfo = bianqianInfoList.get(position);

			View inflate = View.inflate(BianQianActivity.this, R.layout.bianqian_item, null);
			TextView tv_bianqianitem_title = (TextView) inflate.findViewById(R.id.tv_bianqianitem_title);
			TextView tv_bianqianitem_content = (TextView) inflate.findViewById(R.id.tv_bianqianitem_content);
			TextView tv_bianqianitem_date = (TextView) inflate.findViewById(R.id.tv_bianqianitem_date);

			tv_bianqianitem_title.setText(bianqianInfo.getTitle());
			tv_bianqianitem_content.setText(bianqianInfo.getContent());
			tv_bianqianitem_date.setText(bianqianInfo.getDate());

			return inflate;
		}
		
	}
}
