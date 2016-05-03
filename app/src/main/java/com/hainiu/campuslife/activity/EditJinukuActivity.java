package com.hainiu.campuslife.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hainiu.campuslife.bean.JinkuCategory;
import com.hainu.campuslife.R;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class EditJinukuActivity extends ActionBarActivity {

    private static final String TAG = "EditJinukuActivity";
    private EditText ed_jinkuedit_name;
    private EditText ed_jinkuedit_totlacount;
    private EditText ed_jinkuedit_balancecount;
    private ProgressDialog dialog;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jinuku);

        ed_jinkuedit_name = (EditText) findViewById(R.id.ed_jinkuedit_name);
        ed_jinkuedit_totlacount = (EditText) findViewById(R.id.ed_jinkuedit_totlacount);
        ed_jinkuedit_balancecount = (EditText) findViewById(R.id.ed_jinkuedit_balancecount);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String totalAmount = intent.getStringExtra("totalAmount");
        String blanceAmount = intent.getStringExtra("blanceAmount");
        objectId = intent.getStringExtra("objectId");

        ed_jinkuedit_name.setText(name);
        ed_jinkuedit_totlacount.setText(totalAmount);
        ed_jinkuedit_balancecount.setText(blanceAmount);

    }

    public void editJinkuConfirm(View view){
        final String name = ed_jinkuedit_name.getText().toString();
        final String totlacount = ed_jinkuedit_totlacount.getText().toString();
        final String balancecount = ed_jinkuedit_balancecount.getText().toString();

        JinkuCategory jinkuCategory = new JinkuCategory();
        jinkuCategory.setName(name);
        jinkuCategory.setTotalAmount(Integer.parseInt(totlacount));
        jinkuCategory.setBlanceAmount(Integer.parseInt(balancecount));
        jinkuCategory.setObjectId(objectId);
        Log.i(TAG,"objectId:"+objectId);

        showDialog("正在修改...");
        jinkuCategory.update(this, new UpdateListener() {
            @Override
            public void onSuccess() {
                hideDialog();
                Toast.makeText(EditJinukuActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                intent.putExtra("name",name);
                intent.putExtra("totalAmount",totlacount);
                intent.putExtra("blanceAmount",balancecount);
                intent.putExtra("objectId",objectId);
                setResult(131,intent);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                hideDialog();
                Toast.makeText(EditJinukuActivity.this,"修改失败 "+s,Toast.LENGTH_SHORT).show();
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
