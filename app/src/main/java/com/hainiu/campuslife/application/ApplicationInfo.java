package com.hainiu.campuslife.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.hainiu.campuslife.bean.Student;

/**
 * Created by haijun on 2016/5/1.
 */
public class ApplicationInfo extends Application{

    public static SharedPreferences sharedPreferences;
    public static Context mContext;
    public static Student student;



    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("studentInfo", MODE_PRIVATE);
        mContext = this;
    }

    //初始化用户数据
    public static void initStudentInfo(String objectId,String id, String username, String password, String phone, String school,
                                       String major, String sex, String iconUrl){
        student = new Student(id,username,password,phone,school,major,sex, iconUrl);
        student.setObjectId(objectId);
    }

}
