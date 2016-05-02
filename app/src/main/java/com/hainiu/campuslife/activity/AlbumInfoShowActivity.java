package com.hainiu.campuslife.activity;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.AlbumCategory;
import com.hainiu.campuslife.bean.AlbumPicture;
import com.hainiu.campuslife.bean.AlbumPictureTimeList;
import com.hainu.campuslife.R;
import com.lidroid.xutils.BitmapUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class AlbumInfoShowActivity extends Activity {

	private static final String TAG = "AlbumInfoShowActivity";
	private ListView lv_albumshow_shouinfo;

	private List<AlbumPictureTimeList> albumPictureTimeList;
	private TextView tv_albumshow_name;
	private ProgressDialog dialog;
	private String albumName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album_info_show);

		lv_albumshow_shouinfo = (ListView) findViewById(R.id.lv_albumshow_shouinfo);
		tv_albumshow_name = (TextView) findViewById(R.id.tv_albumshow_name);

		Intent intent = getIntent();
		albumName = intent.getStringExtra("albumName");
		tv_albumshow_name.setText(albumName);

		initView();


	}

	private void initView() {
		albumPictureTimeList = new ArrayList<>();
		BmobQuery<AlbumPictureTimeList> bmobQuery = new BmobQuery<>();
		bmobQuery.addWhereEqualTo("type",albumName);
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		bmobQuery.addWhereEqualTo("userId",id);
		bmobQuery.findObjects(this, new FindListener<AlbumPictureTimeList>() {
			@Override
			public void onSuccess(List<AlbumPictureTimeList> list) {
				albumPictureTimeList = list;
				lv_albumshow_shouinfo.setAdapter(new AlbumListDataAdapter());
			}

			@Override
			public void onError(int i, String s) {

			}
		});


		lv_albumshow_shouinfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(AlbumInfoShowActivity.this,AlbumItemShowActivity.class);
				AlbumPictureTimeList albumPictureTimeList = AlbumInfoShowActivity.this.albumPictureTimeList.get(position);
				String picturetime = albumPictureTimeList.getPicturetime();
				String type = albumPictureTimeList.getType();
				intent.putExtra("picturetime",picturetime);
				intent.putExtra("type",type);
				startActivity(intent);
			}
		});

	}

	public void uploadPicture(View view){
		Intent intent = new Intent();
		intent.setAction("android.intent.action.PICK");
		intent.setType("image/*");
		startActivityForResult(intent,100); //请求码100
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//选择图片返回，然后将图片上传
		if (requestCode == 100 && resultCode == RESULT_OK) {
			Uri rUri = data.getData();
			uploadChoosePicture(rUri);
		}
	}

	private void uploadChoosePicture(Uri uri) {
		showDialog("正在上传...");
		//将图片URI转换成存储路径
		String[] imgs1 = {MediaStore.Images.Media.DATA};
		Cursor cursor = this.managedQuery(uri, imgs1, null, null, null);
		int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		String img_url = cursor.getString(index);  //存储路径

		File file = new File(img_url);
		final BmobFile icon = new BmobFile(file);
		//dialog.setMax(100);

		icon.upload(this, new UploadFileListener() {
			@Override
			public void onSuccess() {
				AlbumPicture albumPicture  = new AlbumPicture();
				albumPicture.setPicture(icon);
				albumPicture.setPictureUrl(icon.getUrl());
				String id = ApplicationInfo.sharedPreferences.getString("id","");
				albumPicture.setUserId(id);
				Date date=new Date();//获取时间
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//转换格式
				String formatData = sdf.format(date);
				albumPicture.setTime(formatData);

				//相册时间表中没有这条数据，加入
				AlbumPictureTimeList albumPictureTimeList = new AlbumPictureTimeList();
				albumPictureTimeList.setPicturetime(formatData);
				albumPictureTimeList.setType(albumName);
				albumPictureTimeList.setCount(0);
				albumPictureTimeList.setUserId(id);

				//Bmob批量处理数据
				List<BmobObject> userBeans = new ArrayList<>();
				userBeans.add(albumPicture);
				userBeans.add(albumPictureTimeList);


				BmobObject bmobObject = new BmobObject();
				bmobObject.insertBatch(AlbumInfoShowActivity.this, userBeans, new SaveListener() {
					@Override
					public void onSuccess() {
						Toast.makeText(AlbumInfoShowActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
						initView();
					}

					@Override
					public void onFailure(int i, String s) {
						Toast.makeText(AlbumInfoShowActivity.this,"上传失败"+s,Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onFailure(int i, String ss) {
				hideDialog();
				Toast.makeText(AlbumInfoShowActivity.this,"上传失败"+ss,Toast.LENGTH_SHORT).show();
			}

			/*@Override
			public void onProgress(Integer value) {
				super.onProgress(value);
				Log.i(TAG,"onProgress:"+value);
				Log.i(TAG,"getMax:"+dialog.getMax());
				Log.i(TAG,"getProgress:"+dialog.getProgress());
				dialog.setProgress(value);
			}*/

			@Override
			public void onFinish() {
				super.onFinish();
				hideDialog();
			}

		});
	}

	void showDialog(String message) {
		try {
			if (dialog == null) {
				dialog = new ProgressDialog(this);
				//dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  //设置水平
				dialog.setCancelable(true);
			}
			dialog.setMessage(message);
			dialog.show();
		} catch (Exception e) {
			// 在其他线程调用dialog会报错
		}
	}

	void hideDialog() {
		if (dialog != null && dialog.isShowing())
			try {
				dialog.dismiss();
			} catch (Exception e) {
			}
	}

	class AlbumListDataAdapter extends BaseAdapter{

		private final BitmapUtils bitmapUtils;

		public AlbumListDataAdapter() {
			bitmapUtils = new BitmapUtils(AlbumInfoShowActivity.this);
		}

		@Override
		public int getCount() {
			return albumPictureTimeList.size();
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
			AlbumPictureTimeList albumPictureTimeList = AlbumInfoShowActivity.this.albumPictureTimeList.get(position);
			View inflate = View.inflate(AlbumInfoShowActivity.this, R.layout.item_albuminfor_picture, null);

			TextView tv_albuminfo_data = (TextView) inflate.findViewById(R.id.tv_albuminfo_data);
			final ImageView iv_albuminfo_picture1 = (ImageView) inflate.findViewById(R.id.iv_albuminfo_picture1);
			final ImageView iv_albuminfo_picture2 = (ImageView) inflate.findViewById(R.id.iv_albuminfo_picture2);
			final ImageView iv_albuminfo_picture3 = (ImageView) inflate.findViewById(R.id.iv_albuminfo_picture3);
			final ImageView iv_albuminfo_picture4 = (ImageView) inflate.findViewById(R.id.iv_albuminfo_picture4);

			BmobQuery<AlbumPicture> bmobQuery = new BmobQuery<>();
			bmobQuery.addWhereEqualTo("time",albumPictureTimeList.getPicturetime());
			bmobQuery.addWhereEqualTo("type",albumPictureTimeList.getType());
			bmobQuery.setLimit(4);
			bmobQuery.findObjects(AlbumInfoShowActivity.this, new FindListener<AlbumPicture>() {
				@Override
				public void onSuccess(List<AlbumPicture> list) {
					bitmapUtils.display(iv_albuminfo_picture1,list.get(0).getPictureUrl());
					int size = list.size();
					switch (size){
						case 0:
							break;
						case 1:
							bitmapUtils.display(iv_albuminfo_picture1,list.get(0).getPictureUrl());
							break;
						case 2:
							bitmapUtils.display(iv_albuminfo_picture1,list.get(0).getPictureUrl());
							bitmapUtils.display(iv_albuminfo_picture2,list.get(1).getPictureUrl());
							break;
						case 3:
							bitmapUtils.display(iv_albuminfo_picture1,list.get(0).getPictureUrl());
							bitmapUtils.display(iv_albuminfo_picture2,list.get(1).getPictureUrl());
							bitmapUtils.display(iv_albuminfo_picture3,list.get(2).getPictureUrl());
							break;
						case 4:
							bitmapUtils.display(iv_albuminfo_picture1,list.get(0).getPictureUrl());
							bitmapUtils.display(iv_albuminfo_picture2,list.get(1).getPictureUrl());
							bitmapUtils.display(iv_albuminfo_picture3,list.get(2).getPictureUrl());
							bitmapUtils.display(iv_albuminfo_picture4,list.get(3).getPictureUrl());
							break;
					}

				}

				@Override
				public void onError(int i, String s) {

				}
			});

			tv_albuminfo_data.setText(albumPictureTimeList.getPicturetime());
			return inflate;
		}
	}




}
