/** 
 * File Name:TestActivity.java
 * 
 * Version:1.0
 *
 * Date:2015-8-20
 *
 * Description:
 *
 * Author:erik
 * Email:erik7@126.com
 * Copyright (c) 2015 yxtek Information Co.,Ltd.
 * All Rights Reserved. 
 */ 

package com.yxtek.test.ui.activity;

import java.util.List;

import com.yxtek.ormlite.R;
import com.yxtek.test.db.UserDao;
import com.yxtek.test.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class TestActivity extends Activity implements OnClickListener{

	private static final String TAG = "TestActivity";
	
	private TextView tv;
	
	private EditText etParam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		tv = (TextView)findViewById(R.id.textView1);
		etParam = (EditText)findViewById(R.id.editText1);
		
		findViewById(R.id.btn_delete).setOnClickListener(this);
		findViewById(R.id.btn_add).setOnClickListener(this);
		
		showUserList();
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.btn_delete:
			deleteUsers();
			showUserList();
			break;
		case R.id.btn_add:
			addUsers();
			showUserList();
			break;
		}
	}
	
	private void deleteUsers() {
		String param = etParam.getText().toString();
		
		UserDao dao = new UserDao(this);
		
		if(TextUtils.isEmpty(param)){
			dao.deleteAll();
		}
		else{
			dao.deleteByName(param);
		}
	}
	
	private void showUserList() {
		UserDao dao = new UserDao(this);
		List<User> users = dao.getList();
		if(null==users||users.isEmpty()){
			tv.setText("");
			return;
		}
		
		StringBuilder builder = new StringBuilder();
		
		for(User b:users){
			builder.append("id: "+b.id+"|name: "+b.name+"|age: "+b.age+"\n");
		}
		tv.setText(builder);
	}
	
	private void addUsers() {
		UserDao dao = new UserDao(this);
		
		long tm_start = System.currentTimeMillis();
		
		for(int i=0; i<8; i++){
			User user = new User();
//			user.id = 1000+i;
			user.name = "jack"+i;
			user.age = 22+i;
			dao.add(user);
		}
		
		// 测试数据库插入数据的时间（单位：毫秒）
		long tm_end = System.currentTimeMillis();
		Log.i(TAG, "spend time:"+(tm_end-tm_start));
	}
	
}


