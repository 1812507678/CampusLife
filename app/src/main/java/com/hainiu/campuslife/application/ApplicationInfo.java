package com.hainiu.campuslife.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.hainiu.campuslife.bean.Student;

import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;

import cn.bmob.v3.Bmob;

/**
 * Created by haijun on 2016/5/1.
 */
public class ApplicationInfo extends Application{

    private static final String TAG = "ApplicationInfo";
    public static SharedPreferences sharedPreferences;
    public static Student student;



    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
        sharedPreferences = getSharedPreferences("studentInfo", MODE_PRIVATE);
        Log.i(TAG,"onCreate");

        // 初始化 Bmob SDK
        Bmob.initialize(this, "d8ce0167b5dcba386a96649b4d9ad0d6");

        /*//初始化有米广告
        String appId= "4c14d04b6f8362e9";
        String appSecret = "ab42475b0fbf0120";
        AdManager.getInstance(this).init(appId, appSecret, false);
        SpotManager.getInstance(this).loadSpotAds();*/


    }

    //初始化用户数据
    public static void initStudentInfo(String objectId,String id, String username, String password, String phone, String school,
                                       String major, String sex, String iconUrl){
        student = new Student(id,username,password,phone,school,major,sex, iconUrl);
        student.setObjectId(objectId);
    }

}
