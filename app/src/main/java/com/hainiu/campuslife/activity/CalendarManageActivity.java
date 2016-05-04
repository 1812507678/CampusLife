package com.hainiu.campuslife.activity;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.RemindAndTask;
import com.hainu.campuslife.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class CalendarManageActivity extends Activity {


	private TextView tv_calender_task;
	private TextView tv_calender_remind;
	private DatePicker dp_calender_date;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_manage);

		tv_calender_task = (TextView) findViewById(R.id.tv_calender_task);
		tv_calender_remind = (TextView) findViewById(R.id.tv_calender_remind);
		dp_calender_date = (DatePicker) findViewById(R.id.dp_calender_date);

		initViewData();

	}

	private void initViewData() {
		BmobQuery<RemindAndTask> bmobQuery = new BmobQuery<>();
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		bmobQuery.addWhereEqualTo("userId",id);

		HashSet<String> stringHashMap = new HashSet<>();
		stringHashMap.add("任务");
		stringHashMap.add("提醒");
		//查询出默认的和用户自己上传的相册
		bmobQuery.addWhereContainedIn("type",stringHashMap);

		String date = getDate(dp_calender_date);
		bmobQuery.addWhereEqualTo("date",date);

		bmobQuery.findObjects(this, new FindListener<RemindAndTask>() {
			@Override
			public void onSuccess(List<RemindAndTask> list) {
				String task="";
				String remind="";
				Iterator<RemindAndTask> iterator = list.iterator();
				while (iterator.hasNext()){
					RemindAndTask next = iterator.next();
					if (next.getType().equals("任务")){
						task += next.getContent()+",";
					}
					else if (next.getType().equals("提醒")){
						remind += next.getContent()+",";
					}
				}

				tv_calender_task.setText(task);
				tv_calender_remind.setText(remind);


			}

			@Override
			public void onError(int i, String s) {

			}
		});

		dp_calender_date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	public String getDate(DatePicker datePicker){
		int year = datePicker.getYear();
		int month = datePicker.getMonth()+1;
		int dayOfMonth = datePicker.getDayOfMonth();
		return year+"-"+month+"-"+dayOfMonth;
	}

	public void inquiry(View view){
		initViewData();
	}
}
