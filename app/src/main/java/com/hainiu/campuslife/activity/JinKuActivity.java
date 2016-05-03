package com.hainiu.campuslife.activity;


import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.JinkuCategory;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class JinKuActivity extends Activity {
	protected static final String TAG = "JinKuActivity";
	private GridView gv_jinku_info;
	private List<JinkuCategory> jinkuCategoryList;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinku);
      
        gv_jinku_info = (GridView)findViewById(R.id.gv_jinku_info);
        
        initView();
    }
	
	private void initView() {
		jinkuCategoryList = new ArrayList<>();

		BmobQuery<JinkuCategory> bmobQuery = new BmobQuery<>();
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		bmobQuery.addWhereEqualTo("userId",id);
		bmobQuery.findObjects(this, new FindListener<JinkuCategory>() {
			@Override
			public void onSuccess(List<JinkuCategory> list) {
				jinkuCategoryList = list;
				gv_jinku_info.setAdapter(new JinkuCategoryAdapter());
			}

			@Override
			public void onError(int i, String s) {

			}
		});


		
		gv_jinku_info.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				JinkuCategory jinkuCategory = jinkuCategoryList.get(position);
				Intent intent = new Intent(JinKuActivity.this, JinkuInforShowActivity.class);
				intent.putExtra("name",jinkuCategory.getName());
				intent.putExtra("totalAmount",jinkuCategory.getTotalAmount()+"");
				intent.putExtra("blanceAmount",jinkuCategory.getBlanceAmount()+"");
				intent.putExtra("objectId",jinkuCategory.getObjectId());

				startActivity(intent);
			}
		});
		
	}


	//创建金库按钮，点击跳到创建金库页面
	public void createjinku(View view) {
		Intent intent = new Intent(this, JinkuCreateActivity.class);
		startActivityForResult(intent,110);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==110 && resultCode==111){
			initView();
		}
	}
	
	
	class JinkuCategoryAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return jinkuCategoryList.size();
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
			JinkuCategory jinkuCategory = jinkuCategoryList.get(position);

			View inflate = View.inflate(JinKuActivity.this, R.layout.item_envelope, null);
			TextView tv_envelope_name = (TextView) inflate.findViewById(R.id.tv_envelope_name);
			TextView tv_envelope_totalcount = (TextView) inflate.findViewById(R.id.tv_envelope_totalcount);
			TextView tv_envelope_banalencecount = (TextView) inflate.findViewById(R.id.tv_envelope_banalencecount);

			tv_envelope_name.setText(jinkuCategory.getName());
			tv_envelope_totalcount.setText(jinkuCategory.getTotalAmount()+"");
			tv_envelope_banalencecount.setText(jinkuCategory.getBlanceAmount()+"");

			return inflate;
		}
		
	}

}
