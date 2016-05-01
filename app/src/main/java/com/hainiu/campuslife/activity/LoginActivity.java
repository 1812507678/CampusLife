package com.hainiu.campuslife.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.Student;
import com.hainu.campuslife.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";
    private EditText ed_login_phone;
    private EditText ed_login_password;
    private CheckBox ck_login_savepassword;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_login_phone = (EditText) findViewById(R.id.ed_login_phone);
        ed_login_password = (EditText) findViewById(R.id.ed_login_password);
        ck_login_savepassword = (CheckBox) findViewById(R.id.ck_login_savepassword);

        judgIsLogin();

    }

    private void judgIsLogin() {
        //先判断是否登陆过
        SharedPreferences sharedPreferences = getSharedPreferences("studentInfo", MODE_PRIVATE);
        boolean isRememberInf = sharedPreferences.getBoolean("rememberPassword", false);
        String objectId = sharedPreferences.getString("objectId", "");

        //没有登陆。显示登陆界面
        if (!isRememberInf){
            //什么都不做
        }
        //登陆过，跳到主页面
        else {
            startActivity(new Intent(this,HomeActivity.class));
            saveUserInfo(objectId);
        }
    }

    //用户记住密码时，进入软件，获取用户信息，供其他人使用
    private void saveUserInfo(final String objectId) {

        BmobQuery<Student> bmobQuery = new BmobQuery();
        Log.i(TAG,"objectId"+objectId);

        bmobQuery.getObject(this, objectId, new GetListener<Student>() {
            @Override
            public void onSuccess(Student student) {
                finish();
                Log.i(TAG,"onSuccess");
                //将登陆用户信息保存在ApplicationInfo类的UserInfo对象里面
                saveToApplication(student);
            }

            @Override
            public void onFailure(int i, String s) {
                //登陆失败，初始化数据为空
                ApplicationInfo.initStudentInfo(objectId,"","","","","","","","");
                Toast.makeText(LoginActivity.this,"获取用户信息失败",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public static void saveToApplication(Student student) {
        //将登陆用户信息保存在ApplicationInfo类的UserInfo对象里面
        String objectId = student.getObjectId();
        String id = student.getId();
        String username = student.getUsername();
        String school = student.getSchool();
        String major = student.getMajor();
        String sex = student.getSex();
        String password = student.getPassword();
        String phone = student.getPhone();
        String iconUrl = student.getIconUrl();

        //初始化用户信息
        ApplicationInfo.initStudentInfo(objectId,id,username,password,phone,school,major,sex,iconUrl);
    }

    public void login(View view){
        String phone = ed_login_phone.getText().toString();
        String password = ed_login_password.getText().toString();

        //输入信息验证
        if (phone.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"请输入用户名或密码",Toast.LENGTH_SHORT).show();
            return;
        }
        //登陆验证
        validate(phone,password);
    }

    //验证是否时注册用户
    private void validate(final String phone, final String password) {
        showDialog("正在登陆...");
        //查找Student表里面id为  XXX  的数据
        BmobQuery<Student> bmobQuery = new BmobQuery();

        bmobQuery.addWhereEqualTo("phone",phone);
        bmobQuery.findObjects(this, new FindListener<Student>() {
            @Override
            public void onSuccess(List<Student> list) {
                hideDialog();
                //查询到对应的信息，则注册过，跳到主页面
                if (list.size()==0){
                    //没有查询到对应的信息，则没注册过，提示该用户未注册
                    Toast.makeText(LoginActivity.this,"还未注册，请先进行注册",Toast.LENGTH_SHORT).show();
                }
                else {
                    Student student = list.get(0);
                    if (!student.getPassword().equals(password)){
                        Toast.makeText(LoginActivity.this,"密码不正确",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    saveToApplication(student);

                    boolean checked = ck_login_savepassword.isChecked();
                    //选择记住密码，保存SharedPreferences里，下次不用再登陆
                    if (checked){
                        SharedPreferences.Editor editor = getSharedPreferences("studentInfo", MODE_PRIVATE).edit();
                        editor.putBoolean("rememberPassword",true);
                        editor.putString("objectId",student.getObjectId());
                        editor.commit();
                    }
                    Toast.makeText(LoginActivity.this,"欢迎:"+student.getUsername(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    finish();
                }
            }

            @Override
            public void onError(int i, String s) {
                //登陆失败，初始化数据为空
                String objectId = ApplicationInfo.sharedPreferences.getString("objectId", "");
                ApplicationInfo.initStudentInfo(objectId,"","",phone,password,"", "","","");
                hideDialog();
                Toast.makeText(LoginActivity.this,"登陆失败"+s,Toast.LENGTH_SHORT).show();
            }
        });
    }


    //注册
    public void toRegister(View view){
        //跳到注册页面，注册成功，则跳到主页面
        startActivityForResult(new Intent(this,RegisterActivity.class),120);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //注册成功，页面销毁
        if (resultCode==RESULT_OK && requestCode==120){
            if (data.getBooleanExtra("registOK",false)) {
                finish();
            }
        }
    }

    //忘记密码,进行找回密码
    public void forgetPassword(View view){
        //跳到找回密码页面
        startActivity(new Intent(this,RetrievePasswordActivity.class));
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
