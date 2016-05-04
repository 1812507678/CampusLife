package com.hainiu.campuslife.activity;

import com.hainiu.campuslife.application.ApplicationInfo;
import com.hainiu.campuslife.bean.RemindAndTask;
import com.hainu.campuslife.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class TaskManageActivity extends Activity {

	private ListView lv_taskmanage_showtask;
	private List<RemindAndTask> remindAndTaskList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_manage);
		
		lv_taskmanage_showtask = (ListView) findViewById(R.id.lv_taskmanage_showtask);
		
		initviewData();
	}
	
	private void initviewData() {
		remindAndTaskList = new ArrayList<>();

		BmobQuery<RemindAndTask> bmobQuery = new BmobQuery<>();
		bmobQuery.addWhereEqualTo("type","任务");
		String id = ApplicationInfo.sharedPreferences.getString("id","");
		bmobQuery.addWhereEqualTo("userId",id);
		bmobQuery.findObjects(this, new FindListener<RemindAndTask>() {
			@Override
			public void onSuccess(List<RemindAndTask> list) {
				remindAndTaskList = list;
				lv_taskmanage_showtask.setAdapter(new JinkuInforAdapter());
			}

			@Override
			public void onError(int i, String s) {

			}
		});



	}
	
	public void addTask(View view) {
		Intent intent = new Intent(this, TaskAddActivity.class);
		startActivityForResult(intent,150);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==150 && resultCode==151){
			initviewData();
		}
	}

	class JinkuInforAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return remindAndTaskList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			RemindAndTask remindAndTask = remindAndTaskList.get(remindAndTaskList.size()-1-position);
			View inflate = View.inflate(TaskManageActivity.this, R.layout.item_taskremindshow, null);
			TextView tv_taskremind_date = (TextView) inflate.findViewById(R.id.tv_taskremind_date);
			TextView tv_taskremind_subject = (TextView) inflate.findViewById(R.id.tv_taskremind_subject);

			tv_taskremind_date.setText(remindAndTask.getDate());
			tv_taskremind_subject.setText(remindAndTask.getContent());

			return inflate;
		}
		
	}
}
