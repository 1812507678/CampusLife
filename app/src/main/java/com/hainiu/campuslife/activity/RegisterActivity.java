package com.hainiu.campuslife.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hainiu.campuslife.bean.Student;
import com.hainu.campuslife.R;

import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends Activity {

    private static final String TAG = "RegisterActivity";
    private EditText ed_register_phone;
    private EditText ed_register_password;
    private EditText ed_register_confirmpassword;
    private EditText ed_register_username;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_register_phone = (EditText) findViewById(R.id.ed_register_phone);
        ed_register_password = (EditText) findViewById(R.id.ed_register_password);
        ed_register_confirmpassword = (EditText) findViewById(R.id.ed_register_confirmpassword);
        ed_register_username = (EditText) findViewById(R.id.ed_register_username);

    }

    //注册
    public void register(View view){
        String phone = ed_register_phone.getText().toString();
        String password = ed_register_password.getText().toString();
        String confirmpassword = ed_register_confirmpassword.getText().toString();
        String username = ed_register_username.getText().toString();

        if (phone.isEmpty()){
            Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
        }
        else if(!password.isEmpty() && !confirmpassword.isEmpty() && !password.equals(confirmpassword)){
            Toast.makeText(this,"密码不一致",Toast.LENGTH_SHORT).show();
        }
        else if(username.isEmpty()){
            Toast.makeText(this,"请输入昵称",Toast.LENGTH_SHORT).show();
        }
        else {
            Student student=new Student();
            student.setPhone(phone);
            student.setPassword(password);
            student.setUsername(username);
            student.setId(System.currentTimeMillis()+"");
            insertToDB(student);

        }
    }

    //将数据插入到数据库
    private void insertToDB(final Student student) {
        showDialog("正在注册....");
        student.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                hideDialog();
                Toast.makeText(RegisterActivity.this, "注册成功：" + student.getObjectId(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));

                //将登陆用户信息保存在ApplicationInfo类的student对象里面
                LoginActivity.saveToApplication(student);
                Intent intent = getIntent();
                intent.putExtra("registOK",true);
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void onFailure(int code, String msg) {
                hideDialog();
                Toast.makeText(RegisterActivity.this,"注册失败：" + msg,Toast.LENGTH_SHORT).show();
            }
        });
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
