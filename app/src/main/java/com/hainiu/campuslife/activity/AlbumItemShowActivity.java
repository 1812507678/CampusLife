package com.hainiu.campuslife.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.AlbumPicture;
import com.hainu.campuslife.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class AlbumItemShowActivity extends ActionBarActivity {

    private ListView lv_albumitem_item;
    private List<AlbumPicture> albumPictureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_item_show);

        lv_albumitem_item = (ListView) findViewById(R.id.lv_albumitem_item);

        initViewData();
    }

    private void initViewData() {
        albumPictureList  =new ArrayList<>();
        Intent intent = getIntent();
        String picturetime = intent.getStringExtra("picturetime");
        String type = intent.getStringExtra("type");

        BmobQuery<AlbumPicture> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("time",picturetime);
        bmobQuery.addWhereEqualTo("type",type);
        String id = ApplicationInfo.sharedPreferences.getString("id","");
        bmobQuery.addWhereEqualTo("userId",id);

        bmobQuery.findObjects(this, new FindListener<AlbumPicture>() {
            @Override
            public void onSuccess(List<AlbumPicture> list) {
                albumPictureList = list;
                lv_albumitem_item.setAdapter(new AlbumitemPcitureAdapter());
            }

            @Override
            public void onError(int i, String s) {

            }
        });



    }


    class AlbumitemPcitureAdapter extends BaseAdapter{

        private final BitmapUtils bitmapUtils;

        public AlbumitemPcitureAdapter() {
            bitmapUtils = new BitmapUtils(AlbumItemShowActivity.this);
        }

        @Override
        public int getCount() {
            return albumPictureList.size();
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
            AlbumPicture albumPicture = albumPictureList.get(position);
            View inflate = View.inflate(AlbumItemShowActivity.this, R.layout.item_ablum_pictureitem, null);
            ImageView iv_pictureitem_iamge = (ImageView) inflate.findViewById(R.id.iv_pictureitem_iamge);
            bitmapUtils.display(iv_pictureitem_iamge,albumPicture.getPictureUrl());

            return inflate;
        }
    }


}
