package com.hainiu.campuslife.activity;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.RemindAndTask;
import com.hainu.campuslife.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;

public class RemindAddActivity extends Activity {

	private DatePicker dp_addtask_data;
	private TextView ed_addtask_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_add);

		dp_addtask_data = (DatePicker) findViewById(R.id.dp_addtask_data);
		ed_addtask_content = (TextView) findViewById(R.id.ed_addtask_content);
	}

	public void addtaskOk(View view){
		int year = dp_addtask_data.getYear();
		int month = dp_addtask_data.getMonth()+1;
		int dayOfMonth = dp_addtask_data.getDayOfMonth();
		String date = year+"-"+month+"-"+dayOfMonth;

		String content = ed_addtask_content.getText().toString();

		RemindAndTask remindAndTask = new RemindAndTask();

		remindAndTask.setDate(date);
		remindAndTask.setContent(content);
		remindAndTask.setType("提醒");
		remindAndTask.setRemindorTaskId(System.currentTimeMillis()+"");

		String id = ApplicationInfo.sharedPreferences.getString("id","");
		remindAndTask.setUserId(id);

		remindAndTask.save(this, new SaveListener() {
			@Override
			public void onSuccess() {
				Toast.makeText(RemindAddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
				setResult(151);
				finish();
			}

			@Override
			public void onFailure(int i, String s) {
				Toast.makeText(RemindAddActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
			}
		});


	}


}
