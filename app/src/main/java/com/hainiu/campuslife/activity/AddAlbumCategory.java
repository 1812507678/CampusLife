package com.hainiu.campuslife.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hainiu.campuslife.bean.AlbumCategory;
import com.hainu.campuslife.R;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class AddAlbumCategory extends Activity {

    private static final String TAG = "AddAlbumCategory";
    private ImageView iv_addalbumitem_iamge;
    private Uri uri;
    private EditText tv_addalbumitem_title;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album_category);

        iv_addalbumitem_iamge = (ImageView) findViewById(R.id.iv_addalbumitem_iamge);
        tv_addalbumitem_title = (EditText) findViewById(R.id.tv_addalbumitem_title);
    }

    public void chooseImage(View view){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent,100); //请求码100

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==RESULT_OK){
            uri = data.getData();
            iv_addalbumitem_iamge.setImageURI(uri);
        }
    }

    public void submitData(View view){
        final String name = tv_addalbumitem_title.getText().toString();
        if (!name.isEmpty() && !name.equals("")){
            showDialog("正在上传...");
            //将图片URI转换成存储路径
            String[] imgs1 = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.managedQuery(uri, imgs1, null, null, null);
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String img_url = cursor.getString(index);  //存储路径

            final BmobFile icon = new BmobFile(new File(img_url));

            final AlbumCategory albumCategory  = new AlbumCategory();
            icon.upload(this, new UploadFileListener() {
                @Override
                public void onSuccess() {
                    albumCategory.setIcon(icon);
                    albumCategory.setIconUrl(icon.getUrl());
                    albumCategory.setName(name);
                    albumCategory.setAlbumId(System.currentTimeMillis()+"");
                    Log.i(TAG,albumCategory.toString());

                    //上传成功后,保存相册信息
                    albumCategory.save(AddAlbumCategory.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            hideDialog();
                            Toast.makeText(AddAlbumCategory.this,"上传成功",Toast.LENGTH_SHORT).show();
                            setResult(102);
                            finish();
                        }
                        @Override
                        public void onFailure(int i, String s) {
                            hideDialog();
                            Toast.makeText(AddAlbumCategory.this,"修改失败"+s,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onFailure(int i, String ss) {
                    hideDialog();
                    Log.i(TAG,"上传失败error:   "+ss);
                    Toast.makeText(AddAlbumCategory.this,"上传失败"+ss,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(AddAlbumCategory.this,"输入相册名称",Toast.LENGTH_SHORT).show();
        }
    }

    void showDialog(String message) {
        try {
            if (dialog == null) {
                dialog = new ProgressDialog(this);
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
}
