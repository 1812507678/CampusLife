package com.hainiu.campuslife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.AlbumCategory;
import com.hainu.campuslife.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class AlbumActivity extends Activity {

	private static final String TAG = "AlbumActivity";
	private GridView gv_album_showalbum;
	private List<AlbumCategory> albumCategoryList;
	private AlbumListDataAdapter albumListDataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		gv_album_showalbum = (GridView) findViewById(R.id.gv_album_showalbum);

		initViewData();



	}


	private void initViewData() {
		albumCategoryList = new ArrayList<>();
		//获取网络数据库中所有的相册分类信息
		BmobQuery<AlbumCategory> bmobQuery = new BmobQuery<>();
		String id = ApplicationInfo.sharedPreferences.getString("id","");

		HashSet<String> stringHashMap = new HashSet<>();
		stringHashMap.add("默认");
		stringHashMap.add(id);

		//查询出默认的和用户自己上传的相册
		bmobQuery.addWhereContainedIn("userId",stringHashMap);

		bmobQuery.findObjects(this, new FindListener<AlbumCategory>() {

			@Override
			public void onSuccess(List<AlbumCategory> list) {
				albumCategoryList = list;
				albumListDataAdapter = new AlbumListDataAdapter();
				gv_album_showalbum.setAdapter(albumListDataAdapter);

				Toast.makeText(AlbumActivity.this,"网络数据",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onError(int i, String s) {

			}
		});


		gv_album_showalbum.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(AlbumActivity.this, AlbumInfoShowActivity.class);
				intent.putExtra("albumName",albumCategoryList.get(position).getName());
				startActivity(intent);
			}
		});

	}

	public void addAlbum(View view) {
		Intent intent = new Intent(this, AddAlbumCategoryActivity.class);
		startActivityForResult(intent, 101);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//新建相册成功，重新设置页面布局
		if (requestCode == 101 && resultCode == 102) {
			initViewData();
		}
	}

	class AlbumListDataAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return albumCategoryList.size();
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
			AlbumCategory albumCategory = albumCategoryList.get(position);

			View inflate = View.inflate(AlbumActivity.this, R.layout.item_album, null);
			ImageView iv_albumitem_iamge = (ImageView) inflate.findViewById(R.id.iv_albumitem_iamge);
			TextView tv_albumitem_title = (TextView) inflate.findViewById(R.id.tv_albumitem_title);

			String iconUrl = albumCategory.getIconUrl();
			String name = albumCategory.getName();

			tv_albumitem_title.setText(name);
			BitmapUtils bitmapUtils = new BitmapUtils(AlbumActivity.this);
			bitmapUtils.display(iv_albumitem_iamge, iconUrl);

			return inflate;
		}
	}


}
