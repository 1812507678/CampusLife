package com.hainiu.campuslife.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hainiu.campuslife.db.AlbumCategoryDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haijun on 2016/5/1.
 */
public class AlbumCategory {

    private final SQLiteDatabase readableDatabase;

    public AlbumCategory(Context context) {
        AlbumCategoryDBHelper albumCategoryDBHelper = new AlbumCategoryDBHelper(context, null, null, 1);
        readableDatabase = albumCategoryDBHelper.getReadableDatabase();
    }

    //将图片数据保存在数据库里
    public long insertToAlbum(String name,String imageName){
        ContentValues contentValues  = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("imageName",imageName);
        long albumCategory = readableDatabase.insert("albumCategory", null, contentValues);
        return albumCategory;
    }

    //获取数据库里所有的图片信息
    public List<Map<String,String>> getAllAlbum(){
        Cursor albumCategory = readableDatabase.query("albumCategory", new String[]{"_id","name", "imageName"}, null, null, null, null,null);

        List<Map<String,String>> albumCategoryList = new ArrayList<>();

        while (albumCategory.moveToNext()){
            Map<String,String>  map1 = new HashMap<>();
            map1.put("_id",albumCategory.getString(0));

            Map<String,String>  map2 = new HashMap<>();
            map2.put("_id",albumCategory.getString(1));

            Map<String,String>  map3 = new HashMap<>();
            map3.put("_id",albumCategory.getString(2));

            albumCategoryList.add(map1);
            albumCategoryList.add(map2);
            albumCategoryList.add(map3);
        }
        return albumCategoryList;
    }
}
