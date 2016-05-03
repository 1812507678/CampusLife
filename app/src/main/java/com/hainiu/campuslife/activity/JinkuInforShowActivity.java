package com.hainiu.campuslife.activity;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.JinkuCategory;
import com.hainiu.campuslife.bean.KinkuPayDetial;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class JinkuInforShowActivity extends Activity {

	private ListView lv_jinkuinfor_item;
	private String name;
	private List<KinkuPayDetial> jinkuCategoryList;
	private String totalAmount;
	private String blanceAmount;
	private String objectId;
	private TextView tv_jinkuinfo_type;
	private TextView tv_jinkuinfo_totalt;
	private TextView tv_jinkuinfo_balance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinku_infor_show);

		lv_jinkuinfor_item = (ListView) findViewById(R.id.lv_jinkuinfor_item);
		tv_jinkuinfo_type = (TextView) findViewById(R.id.tv_jinkuinfo_type);
		tv_jinkuinfo_totalt = (TextView) findViewById(R.id.tv_jinkuinfo_totalt);
		tv_jinkuinfo_balance = (TextView) findViewById(R.id.tv_jinkuinfo_balance);

		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		totalAmount = intent.getStringExtra("totalAmount");
		blanceAmount = intent.getStringExtra("blanceAmount");
		objectId = intent.getStringExtra("objectId");

		tv_jinkuinfo_type.setText(name);
		tv_jinkuinfo_totalt.setText(totalAmount);
		tv_jinkuinfo_balance.setText(blanceAmount);

		initviewData();
	}

	private void initviewData() {
		jinkuCategoryList = new ArrayList<>();
		BmobQuery<KinkuPayDetial> bmobQuery = new BmobQuery<>();
		bmobQuery.addWhereEqualTo("blongToJinku",name);
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		bmobQuery.addWhereEqualTo("userId",id);
		bmobQuery.order("payData");   //按日期排序
		bmobQuery.findObjects(this, new FindListener<KinkuPayDetial>() {
			@Override
			public void onSuccess(List<KinkuPayDetial> list) {
				jinkuCategoryList = list;
				lv_jinkuinfor_item.setAdapter(new JinkuInforAdapter());
			}

			@Override
			public void onError(int i, String s) {

			}
		});
	}

	public void addjinkuiitem(View view) {
		Intent intent = new Intent(this, JinkuAddActivity.class);
		intent.putExtra("name",name);
		intent.putExtra("blanceAmount",blanceAmount+"");
		intent.putExtra("objectId",objectId);
		startActivityForResult(intent,120);

	}

	public void editjinku(View view){
		Intent intent = new Intent(this, EditJinukuActivity.class);
		intent.putExtra("name",name);
		intent.putExtra("totalAmount",totalAmount+"");
		intent.putExtra("blanceAmount",blanceAmount+"");
		intent.putExtra("objectId",objectId);
		startActivityForResult(intent,130);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode==120 && resultCode==121){
			initviewData();
			blanceAmount = data.getStringExtra("blanceAmount");
			tv_jinkuinfo_balance.setText(blanceAmount);
		}
		else if (requestCode==130 && resultCode==131){
			name = data.getStringExtra("name");
			totalAmount = data.getStringExtra("totalAmount");
			blanceAmount = data.getStringExtra("blanceAmount");
			tv_jinkuinfo_type.setText(name);
			tv_jinkuinfo_totalt.setText(totalAmount);
			tv_jinkuinfo_balance.setText(blanceAmount);
		}
	}

	class JinkuInforAdapter extends BaseAdapter{

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
			KinkuPayDetial kinkuPayDetial = jinkuCategoryList.get(jinkuCategoryList.size()-1-position);

			View inflate = View.inflate(JinkuInforShowActivity.this, R.layout.item_jinkushow, null);
			TextView tv_itemshow_date = (TextView) inflate.findViewById(R.id.tv_itemshow_date);
			TextView tv_itemshow_paycount = (TextView) inflate.findViewById(R.id.tv_itemshow_paycount);
			TextView tv_itemshow_paysubject = (TextView) inflate.findViewById(R.id.tv_itemshow_paysubject);

			tv_itemshow_date.setText(kinkuPayDetial.getPayData());
			tv_itemshow_paycount.setText(kinkuPayDetial.getPayCount()+"");
			tv_itemshow_paysubject.setText(kinkuPayDetial.getPaySubject());

			return inflate;
		}
		
	}
}
