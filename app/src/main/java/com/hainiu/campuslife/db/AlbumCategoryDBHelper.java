package com.hainiu.campuslife.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by haijun on 2016/5/1.
 */
public class AlbumCategoryDBHelper extends SQLiteOpenHelper{
    public AlbumCategoryDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "campuslife.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建albumCategory表
        String sql = "create table albumCategory(_id Integer primary key autoincrement,name varchar(100),imageName varchar(100))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
